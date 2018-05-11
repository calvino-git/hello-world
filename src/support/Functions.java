/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import asycuda.Awmds;
import database.BillOfLanding;
import database.BillOfLandingJpaController;
import database.Container;
import database.ContainerJpaController;
import database.GeneralInfo;
import database.GeneralInfoJpaController;
import exceptions.IllegalOrphanException;
import exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;

/**
 *
 * @author CALVINO
 */
public class Functions{
    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("ASYCUDAPU");
    static AlertMsg alertMsg = new AlertMsg();

    //transforme a manifest XML file into an object of Awmds in order to using the data in it 
    public static Awmds xmlToAwmds(File xmlFichier) throws JAXBException {
        Awmds mani = new Awmds();
        try{
            JAXBContext jc = JAXBContext.newInstance(Awmds.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            
            mani = (Awmds) unmarshaller.unmarshal(xmlFichier);
            return mani;
        }catch( UnmarshalException ue ) {
            ue.printStackTrace();
            System.out.println( "Fichier XML non valide" + ue.getLocalizedMessage());
        } catch( JAXBException je ) { 
            je.printStackTrace();
        }
        return mani;
    }
 
    //Take the instance of Awmds object and save into a xml file in a specific location
    public static void AwmdsToXml(Awmds awmds, File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(Awmds.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Marshalling and saving XML to the file.
        m.marshal(awmds, file);
        
    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Problème rencontré lors de la génération du XML");
        alert.setContentText("Impossible de générer le fichier :\n" + file.getPath());

        alert.showAndWait();
    }
}
    
    //import a XML manifest to fill all the fields in order of editing the manifest or checking it out
    public static Awmds selectXml(File xmlFile){
        Awmds man = null;
        if(xmlFile != null) {
			try {
                                System.out.println(xmlFile.getName());
                                man = xmlToAwmds(xmlFile);
                                System.out.println(man.getGeneralSegment().getGeneralSegmentId().getCustomsOfficeCode());
				return man;
			} catch(Exception e) {
                                e.printStackTrace();
				alertMsg.alertMsg(Alert.AlertType.ERROR, "Manifest XML", "Erreur",null);
			}
		} else {
			alertMsg.alertMsg(Alert.AlertType.ERROR, "Manifest XML", "Aucune manifest selectionné", null);
                        return null;
		}
        return man;
    }   
    
    public static int persistToDB(Awmds cargo) throws NonexistentEntityException, IllegalOrphanException {
        
        GeneralInfo gen = getGeneral(cargo);
        System.out.println(gen.toString() + " " + gen.getIdentityoftransporter());

        GeneralInfoJpaController gijc = new GeneralInfoJpaController(EMF);
        gen.setId(gijc.findLastId());
        try{
            gijc.create(gen);
            List<BillOfLanding> bols = getBol(cargo,gen);
            gen.setBillOfLandingCollection(bols);
        } catch (Exception ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return gen.getId();
           
    }
    
    /**
     * Recuperation des informations generales du manifest
     * La methode prend en parametre un manifest complet et retourne un objet contenant les info generales
     */
    private static GeneralInfo getGeneral(Awmds awmds) {
        GeneralInfo general = new GeneralInfo();
        general.setCustomsOfficeCode(awmds.getGeneralSegment().getGeneralSegmentId().getCustomsOfficeCode());
        general.setVoyageNumber(awmds.getGeneralSegment().getGeneralSegmentId().getVoyageNumber());
        general.setDateDeparture(awmds.getGeneralSegment().getGeneralSegmentId().getDateOfDeparture());
        general.setDateArrival(awmds.getGeneralSegment().getGeneralSegmentId().getDateOfArrival());
        general.setDateLastDischarge(awmds.getGeneralSegment().getGeneralSegmentId().getDateOfLastDischarge());
        general.setTimeArrival(awmds.getGeneralSegment().getGeneralSegmentId().getTimeOfArrival());

        //recuperer les donnees du segment <TotalsSegment>
        general.setTotalnumberofpackages(String.valueOf(awmds.getGeneralSegment().getTotalsSegment().getTotalNumberOfPackages()));
        general.setTotalnumberofcontainers(String.valueOf(awmds.getGeneralSegment().getTotalsSegment().getTotalNumberOfContainers()));
        general.setTotalgrossmass(String.valueOf(awmds.getGeneralSegment().getTotalsSegment().getTotalGrossMass()));
        general.setTotalNumberOfBols(String.valueOf(awmds.getGeneralSegment().getTotalsSegment().getTotalNumberOfBols()));

        //recuperation des donnes du segment <Transport_informations>
        //segment <Carrier>
        general.setCarriername(awmds.getGeneralSegment().getTransportInformation().getCarrier().getCarrierName());
        general.setCarriercode(awmds.getGeneralSegment().getTransportInformation().getCarrier().getCarrierCode());
        general.setCarrieraddress(awmds.getGeneralSegment().getTransportInformation().getCarrier().getCarrierAddress());

        general.setModeoftransportcode(awmds.getGeneralSegment().getTransportInformation().getModeOfTransportCode());
        general.setIdentityoftransporter(awmds.getGeneralSegment().getTransportInformation().getIdentityOfTransporter());
        general.setNationalityoftransportercode(awmds.getGeneralSegment().getTransportInformation().getNationalityOfTransporterCode());
        
        if(awmds.getGeneralSegment().getTransportInformation().getMasterInformation() == null)
            general.setMasterinformation("DEFAULT");

        if (awmds.getGeneralSegment().getTransportInformation().getPlaceOfTransporter() != null) {
            general.setPlaceoftransporter(awmds.getGeneralSegment().getTransportInformation().getPlaceOfTransporter());
        }

        general.setRegistrationnumberoftransportcode(awmds.getGeneralSegment().getTransportInformation().getRegistrationNumberOfTransportCode());
        general.setDateofregistration(awmds.getGeneralSegment().getTransportInformation().getDateOfRegistration());

        //segment <Shipping agent>
        if (awmds.getGeneralSegment().getTransportInformation().getShippingAgent() != null) {
            general.setShippingAgentname(awmds.getGeneralSegment().getTransportInformation().getShippingAgent().getShippingAgentName());
            general.setShippingAgentcode(awmds.getGeneralSegment().getTransportInformation().getShippingAgent().getShippingAgentCode());
        }
        general.setPlaceofdeparturecode(awmds.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDepartureCode());
        general.setPlaceofdestinationcode(awmds.getGeneralSegment().getLoadUnloadPlace().getPlaceOfDestinationCode());

        if (awmds.getGeneralSegment().getTonnage() != null) {
            general.setTonnagenetweight(String.valueOf(awmds.getGeneralSegment().getTonnage().getTonnageNetWeight()));
            general.setTonnagegrossweight(String.valueOf(awmds.getGeneralSegment().getTonnage().getTonnageGrossWeight()));
        }
        System.out.println(" General info created!!!" + general.toString());
        return general;
    } 
    
    /**
     * La methode prend en parametre un objet manifest complet et un objet info general puis retourne une liste de bill of landing
     */
    protected static List<BillOfLanding> getBol(Awmds awmds, GeneralInfo general) throws NullPointerException {
        List<BillOfLanding> bols = new ArrayList();
        if(!awmds.getBolSegment().isEmpty()){
            awmds.getBolSegment().stream().forEach((bol) -> {
                try {
                    BillOfLanding bl = new BillOfLanding();
                    bl.setBolnature(bol.getBolId().getBolNature());
                    bl.setBolreference(bol.getBolId().getBolReference());
                    bl.setBoltypecode(bol.getBolId().getBolTypeCode());

                    if(bol.getTradersSegment().getConsignee().getConsigneeAddress() != null){
                        bl.setConsigneeaddress(bol.getTradersSegment().getConsignee().getConsigneeAddress());
                    }else{
                        bl.setConsigneeaddress("consignee address");
                    }

                    bl.setConsigneecode(bol.getTradersSegment().getConsignee().getConsigneeCode());
                    bl.setConsigneename(bol.getTradersSegment().getConsignee().getConsigneeName());

                    if (!(bol.getValueSegment().getCustomsSegment() == null)){
                        bl.setCustomscurrency(bol.getValueSegment().getCustomsSegment().getCustomsCurrency());
                        bl.setCustomsvalue(String.valueOf(bol.getValueSegment().getCustomsSegment().getCustomsValue()));
                    }
                    bl.setExporteraddress(bol.getTradersSegment().getExporter().getExporterAddress());
                    bl.setExportercode(bol.getTradersSegment().getExporter().getExporterCode());
                    bl.setExportername(bol.getTradersSegment().getExporter().getExporterName());
                    bl.setGoodsdescription(bol.getGoodsSegment().getGoodsDescription());
                    bl.setGrossmass(String.valueOf(bol.getGoodsSegment().getGrossMass()));
                    bl.setInformation(bol.getGoodsSegment().getInformation());
                    if (!(bol.getValueSegment().getInsuranceSegment() == null)) {
                        bl.setInsurancecurrency(bol.getValueSegment().getInsuranceSegment().getInsuranceCurrency());
                        bl.setInsurancevalue(String.valueOf(bol.getValueSegment().getInsuranceSegment().getInsuranceValue()));
                    }
                    bl.setLinenumber(String.valueOf(bol.getBolId().getLineNumber().getValue()));
                    bl.setLocationcode(bol.getLocation().getLocationCode());
                    bl.setLocationinfo(bol.getLocation().getLocationInfo());
                    bl.setMasterbolrefnumber(bol.getBolId().getMasterBolRefNumber());
                    bl.setNotifyaddress(bol.getTradersSegment().getNotify().getNotifyAddress());
                    bl.setNotifycode(bol.getTradersSegment().getNotify().getNotifyCode());
                    bl.setNotifyname(bol.getTradersSegment().getNotify().getNotifyName());
                    bl.setNumberofpackages(String.valueOf(bol.getGoodsSegment().getNumberOfPackages()));
                    bl.setNumofctnforthisbol(String.valueOf(bol.getGoodsSegment().getNumOfCtnForThisBol()));
                    bl.setPackagetypecode(bol.getGoodsSegment().getPackageTypeCode());

                    bl.setPlaceofloadingcode(bol.getLoadUnloadPlace().getPlaceOfLoadingCode());
                    bl.setPlaceofunloadingcode(bol.getLoadUnloadPlace().getPlaceOfUnloadingCode());

                    bl.setShippingmarks(bol.getGoodsSegment().getShippingMarks());
                    if (!(bol.getValueSegment().getTransportSegment() == null)) {
                        bl.setTransportcurrency(bol.getValueSegment().getTransportSegment().getTransportCurrency());
                        bl.setTransportvalue(String.valueOf(bol.getValueSegment().getTransportSegment().getTransportValue()));
                    }
                    if (!(bol.getValueSegment().getFreightSegment() == null)) {
                        bl.setPCindicator(bol.getValueSegment().getFreightSegment().getPCIndicator());
                        bl.setFreightcurrency(bol.getValueSegment().getFreightSegment().getFreightCurrency());
                        bl.setFreightvalue(String.valueOf(bol.getValueSegment().getFreightSegment().getFreightValue()));
                    }
                    bl.setUniquecarrierreference(bol.getBolId().getUniqueCarrierReference());
                    if(bol.getGoodsSegment().getVolumeInCubicMeters()== null)
                        bol.getGoodsSegment().setVolumeInCubicMeters(0.0);
                    bl.setVolumeincubicmeters(String.valueOf(bol.getGoodsSegment().getVolumeInCubicMeters()));
                    bl.setIdGeneral(general);
                    // Persistence
                    BillOfLandingJpaController boljc = new BillOfLandingJpaController(EMF);
                    bl.setIdBol(boljc.findLastId());
                    boljc.create(bl);
                    ////
                    bl.setContainerCollection(getContainer(bol, bl));

                    System.out.println(" Bol " + bl.getIdBol() + " du Manifest " + bl.getIdGeneral().getId() + " has " + bl.getContainerCollection().size() + " Conteneurs");
                    bols.add(bl);
                } catch (Exception ex) {
                    Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        return bols;
    }

    /**
     * @Description 
     * @param bolsegment est un B/L donné dans le manifest
     * @param bol une instance contenant les donnees de bolsegment afin d etre
     * enregistrer dans la base de donnee
     * @return une liste de conteneurs que contient un B/L
     */
    protected static List<Container> getContainer(Awmds.BolSegment bolsegment, BillOfLanding bol) {
        List<Container> ctnrs = new ArrayList();
        /**
         * recuperation des donnees concernant les conteneurs contenus dans le
         * B/L bol depuis un instance de la classe JAXB Awmds.bolsegment qui
         * contient les donnees du xml manifest
         */
        if(!bolsegment.getCtnSegment().isEmpty()){
            bolsegment.getCtnSegment().stream().forEach((ctn) -> {
                try {
                    Container newctn = new Container();
                    newctn.setCtnreference(ctn.getCtnReference());
                    newctn.setEmptyFull(ctn.getEmptyFull());
                    newctn.setEmptyweight(String.valueOf(ctn.getEmptyWeight()));
                    if(ctn.getGoodsWeight() == null)
                        ctn.setGoodsWeight(0.0);
                    newctn.setGoodsweight(String.valueOf(ctn.getGoodsWeight()));
                    newctn.setIdBol(bol);
                    newctn.setMarks1(ctn.getMarks1());
                    newctn.setMarks2(ctn.getMarks2());
                    newctn.setMarks3(ctn.getMarks3());
                    newctn.setNumberofpackages(String.valueOf(ctn.getNumberOfPackages()));
                    newctn.setSealingParty(ctn.getSealingParty());
                    newctn.setTypeofcontainer(ctn.getTypeOfContainer());

                    //**Enregistrement du conteneur dans la base de donnees a la table CONTAINER**//
                    ContainerJpaController boljc = new ContainerJpaController(EMF);
                    newctn.setIdCtn(boljc.findLastId());
                    boljc.create(newctn);
                    ctnrs.add(newctn); // 
                } catch (Exception ex) {
                    Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        return ctnrs;
    }
    
    
}