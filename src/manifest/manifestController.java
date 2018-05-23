/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manifest;

import database.ContainerJpaController;
import database.GeneralInfoJpaController;
import database.Container;
import database.BillOfLandingJpaController;
import support.AlertMsg;
import support.Functions;
import support.referenceTable;
import asycuda.ObjectFactory;
import asycuda.*;
import exceptions.IllegalOrphanException;
import exceptions.NonexistentEntityException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import support.ComboBoxAutoComplete;

/**
 * FXML Controller class
 *
 * @author CALVINO
 */
public class manifestController implements Initializable {

    @FXML
    private Font x3;
    @FXML
    private VBox vboxmain;
    @FXML
    private Pane pane;
    @FXML
    private TreeView<String> tree2;
    @FXML
    private TextField custumOfficName;
    @FXML
    private TextField voyageNumber;
    @FXML
    private ComboBox<String> departurePlaceCode;
    @FXML
    private TextField departurePlaceName;
    @FXML
    private ComboBox<String> destPlaceCode;
    @FXML
    private TextField destPlaceName;
    @FXML
    private DatePicker arrivalDate;
    @FXML
    private TextField arrivalTime;
    @FXML
    private TextField tonnageGross;
    @FXML
    private TextField tonnageNet;
    @FXML
    private TextField carrierCode;
    @FXML
    private TextField carrierName;
    @FXML
    private TextArea carrierAdress;
    @FXML
    private TextField shippingAgentCode;
    @FXML
    private TextField shippingAgentName;
    @FXML
    private TextField totalNbrBol;
    @FXML
    private TextField totalNbrPkg;
    @FXML
    private TextField totalNbrCtn;
    @FXML
    private TextField totalGrossMass;
    @FXML
    private ComboBox<String> transportModeCode;
    @FXML
    private TextField transportModeName;
    @FXML
    private ComboBox<String> transportNationalityCode;
    @FXML
    private TextField transportNationalityName;
    private TextField transportRegistration;
    @FXML
    private TextField transportMaster;
    @FXML
    private TextField transportIdentity;
    @FXML
    private TextField transportPlace;
    private DatePicker trasnportDate;
    @FXML
    private ComboBox<String> customOfficeCode;
    @FXML
    private DatePicker departureDate;
    @FXML
    private Label statusL;
    @FXML
    private Label statusR;
    @FXML
    private TextField blVoyageNbr;
    @FXML
    private TextField blRefNbr;
    @FXML
    private ComboBox<String> blTypeCode;
    @FXML
    private TextField blTypeName;
    @FXML
    private DatePicker blDepDate;
    @FXML
    private ComboBox<String> blPlaceLoadCode;
    @FXML
    private TextField blPlaceLoadName;
    @FXML
    private ComboBox<String> blPlaceUnloadCode;
    @FXML
    private TextField blPlaceUnloadName;
    private TextField blUCR;
    @FXML
    private DatePicker blArrivalDate;
    @FXML
    private TextField blArrivalTime;
    @FXML
    private TextField blNbr1;
    @FXML
    private TextField blNbr2;
    @FXML
    private ComboBox<String> blNature;
    @FXML
    private DatePicker blLastDischarge;
    @FXML
    private TextField blShipAgentCode;
    @FXML
    private TextField blShipAgentName;
    @FXML
    private TextField blCarrierCode;
    @FXML
    private TextField blCarrierName;
    @FXML
    private TextArea blCarrierAddress;
    @FXML
    private TextArea info;
    @FXML
    private TextField blexporterCode;
    @FXML
    private TextField blexporterName;
    @FXML
    private TextArea blexporterAddress;
    @FXML
    private TextField blnotifyCode;
    @FXML
    private TextField blnotifyName;
    @FXML
    private TextArea blnotifyAddress;
    @FXML
    private TextField blconsigneeCode;
    @FXML
    private TextField blConsigneeName;
    @FXML
    private TextArea blconsigneeAddress;
    @FXML
    private ComboBox<String> blpkgCode;
    @FXML
    private TextField blpkgName;
    @FXML
    private TextField blvolumeCBM;
    @FXML
    private TextArea blmarknb;
    @FXML
    private TextField blpcInd;
    @FXML
    private TextField blamount;
    @FXML
    private TextField blcurrency;
    @FXML
    private ComboBox<String> blportCode;
    @FXML
    private TextField blportName;
    private TextArea blportAddress;
    @FXML
    private TextField blcustom1;
    @FXML
    private TextField blcustom2;
    @FXML
    private TextField bltransport1;
    @FXML
    private TextField bltransport2;
    @FXML
    private TextField blinsurance1;
    @FXML
    private TextField blinsurance2;
    @FXML
    private TextField blsealNbr;
    @FXML
    private TextField blmarks;
    @FXML
    private TextField blparty1;
    @FXML
    private TextField blparty2;
    @FXML
    private TextArea bldescGoods;
    @FXML
    private TextField ctnNbr;
    @FXML
    private ComboBox<String> ctnType;
    @FXML
    private ComboBox<String> ctnFcl;
    @FXML
    private TextField ctnSeals;
    @FXML
    private TextField ctnMarks1;
    @FXML
    private TextField ctnMarks2;
    @FXML
    private ComboBox<String> ctnSealingParty;
    @FXML
    private TextField ctnEmptyWeight;
    @FXML
    private TextField ctnGoodsWeight;
    @FXML
    private Tab ctn;
    @FXML
    private TabPane tabPane;
    @FXML
    private AnchorPane ctnAnchor;
    @FXML
    private Tab bol;
    @FXML
    private AnchorPane manifestArea;
    @FXML
    private AnchorPane bolAnchor;
    @FXML
    private Tab manifestTab;
    @FXML
    private TableView<Container> ctnTable;
    @FXML
    private TableColumn<Container, String> refCtn;
    @FXML
    private TableColumn<Container, String> nbrPkgCtn;
    @FXML
    private TableColumn<Container, String> typeCtn;
    @FXML
    private TableColumn<Container, String> EFCtn;
    @FXML
    private TableColumn<Container, String> marks1Ctn;
    @FXML
    private TableColumn<Container, String> marks2Ctn;
    @FXML
    private TableColumn<Container, String> sealPartyCtn;
    @FXML
    private TableColumn<Container, String> emptyCtn;
    @FXML
    private TableColumn<Container, String> goodsCtn;
    @FXML
    private Button nouveau;
    @FXML
    private Button enregistrer;
    @FXML
    private Button importXML;
    @FXML
    private Button enregistrerXML;
    @FXML
    private TextField blgrossMass;
    @FXML
    private TextField blnbrCtn;
    @FXML
    private TextField blnbrPkgs;
    @FXML
    private TextField ctnRef;
    @FXML
    private Label client;
    @FXML
    private TableColumn<?, ?> sealCtn;    
    
    final ObservableList<Container> data = FXCollections.observableArrayList();
    public static Awmds manifest;
    private String filter;
    private ObservableList<String> originalItems;
    private final int times = 0; 
    private int lineBol = 0;
    private int lineCtn = 0;
    private static int lineMan = 0;
    private referenceTable ref;
    private File file;
    protected static TreeItem<String> cargo = new TreeItem<>(LocalDate.now().format(DateTimeFormatter.ofPattern("E dd MMMM yyyy",Locale.FRENCH)));
    private final ObjectFactory obj = new ObjectFactory();
    private Awmds awmds = obj.createAwmds();
    private Awmds.GeneralSegment currentGeneralSegment = obj.createAwmdsGeneralSegment();
    private Awmds.BolSegment currentBol = obj.createAwmdsBolSegment();
    
    private GeneralInfoJpaController generalctrl = new GeneralInfoJpaController(Functions.EMF);
    private BillOfLandingJpaController bolctrl = new BillOfLandingJpaController(Functions.EMF);
    private ContainerJpaController ctnctrl = new ContainerJpaController(Functions.EMF);
    
    private static TreeItem<String> item, bolTree;
//    private Map<TreeItem,Awmds.GeneralSegment> gs = new Map<TreeItem, Awmds.GeneralSegment>;
//    private Map<TreeItem,Awmds.BolSegment> tgs;
//    private Map<TreeItem,Awmds.BolSegment.CtnSegment> qgs;
    private ObservableList<HashMap<TreeItem,Awmds.GeneralSegment>> obs1;
    private ObservableList<HashMap<TreeItem,Awmds.BolSegment>> obs2;
    private ObservableList<HashMap<TreeItem,Awmds.BolSegment.CtnSegment>> obs3;
    
    private LinkedHashMap<TreeItem,Awmds.GeneralSegment> ags = new LinkedHashMap<>();
    private LinkedHashMap<TreeItem,Awmds.BolSegment> abs = new LinkedHashMap<>();
    private LinkedHashMap<TreeItem,Awmds.BolSegment.CtnSegment> abc = new LinkedHashMap<>();
    @FXML
    private AnchorPane mainWindow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        ref = new referenceTable();
        setContextMenu(tree2);
        ctnTable.setVisible(false);
        client.setText("CONGO ENERGY SERVICES");
        customOfficeCode.setValue("DD141");
        custumOfficName.setText("Bureau Principal Port");
        customOfficeCode.setDisable(true);
        blTypeCode.setValue("CO2");
        ctn.setDisable(true);
        bol.setDisable(true);
        
        importXML.setDisable(true);
        enregistrerXML.setDisable(false);
        System.out.println("Demarrage du programme...");
        
        
        TextField test = new TextField();
        loadComboBox(blportCode, blportName, ref.localisation, 6);
        loadComboBox(blNature, test, ref.nature,2);
        loadComboBox(blPlaceLoadCode, blPlaceLoadName, ref.locode,5);
        loadComboBox(blPlaceUnloadCode, blPlaceUnloadName, ref.locode,5);
        //  loadComboBox(blportCode, blportName, ref.locode);
        //  loadComboBox(blManifestCode, blManifestName, ref.cuoCod,5);
        loadComboBox(blpkgCode, blpkgName, ref.pkg_table,2);
        loadComboBox(ctnType, test, ref.ctn_type,2);
        loadComboBox(ctnFcl, test, ref.ctn_ind,3);
        loadComboBox(ctnSealingParty, test, ref.sealing_party,3);
        loadComboBox(departurePlaceCode, departurePlaceName, ref.locode,5);
        loadComboBox(customOfficeCode, custumOfficName, ref.cuoCod,5);
        loadComboBox(destPlaceCode, destPlaceName, ref.locode,5);
        loadComboBox(transportModeCode, transportModeName, ref.mode_trans,2);
        loadComboBox(transportNationalityCode, transportNationalityName, ref.country,2);
        
        new ComboBoxAutoComplete<>(blportCode);
        new ComboBoxAutoComplete<>(customOfficeCode);
        new ComboBoxAutoComplete<>(departurePlaceCode);
        new ComboBoxAutoComplete<>(destPlaceCode);
        new ComboBoxAutoComplete<>(transportModeCode);
        new ComboBoxAutoComplete<>(transportNationalityCode);
        new ComboBoxAutoComplete<>(blPlaceLoadCode);
        new ComboBoxAutoComplete<>(blPlaceUnloadCode);
        new ComboBoxAutoComplete<>(blPlaceLoadCode);
        new ComboBoxAutoComplete<>(blNature);
        new ComboBoxAutoComplete<>(blpkgCode);
        new ComboBoxAutoComplete<>(ctnType);
        new ComboBoxAutoComplete<>(ctnFcl);
        new ComboBoxAutoComplete<>(ctnSealingParty);
        
        blVoyageNbr.textProperty().bind(voyageNumber.textProperty());
        blDepDate.valueProperty().bind(departureDate.valueProperty());
        blNbr2.textProperty().bind(totalNbrBol.textProperty());
        blArrivalDate.valueProperty().bind(arrivalDate.valueProperty());
        blCarrierCode.textProperty().bind(carrierCode.textProperty());
        blCarrierName.textProperty().bind(carrierName.textProperty());
        blCarrierAddress.textProperty().bind(carrierAdress.textProperty());
        
        nouveau.setOnAction(e ->{ 
                clear(manifestTab.getContent());
                clear(bol.getContent());
                clear(ctn.getContent());
                cargo.getChildren().clear();
                manifestTab.setDisable(false);
                bol.setDisable(true);
                ctn.setDisable(true);
        });
        // 
        enregistrer.setOnAction(e -> {
            try {
                saveManifest(e);
            } catch (NonexistentEntityException | IllegalOrphanException ex) {
                Logger.getLogger(manifestController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        // 
        tabPane.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) -> {
            if(newValue.equals(manifestTab)){
                nouveau.setOnAction(e -> clear(manifestTab.getContent()));
                nouveau.setText("Vider");
                
                manifestTab.setDisable(true);
                bol.setDisable(false);
                ctn.setDisable(false);
                
                enregistrer.setOnAction(event -> {
                    try {
                        saveManifest(event);
                    } catch (NonexistentEntityException | IllegalOrphanException ex) {
                        Logger.getLogger(manifestController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if(newValue.equals(bol)){ 
                nouveau.setOnAction(e -> clear(bol.getContent()));
                nouveau.setText("Vider");
                
                manifestTab.setDisable(false);
                bol.setDisable(true);
                ctn.setDisable(false);
                
                enregistrer.setOnAction(event -> saveBol(event));
            }
            if(newValue.equals(ctn)){
                nouveau.setOnAction(e -> clear(ctn.getContent()));
                nouveau.setText("Vider");
                
                manifestTab.setDisable(false);
                bol.setDisable(false);
                ctn.setDisable(true);
                
                enregistrer.setOnAction(event -> {
                    try {
                        saveCtn(event);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(manifestController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getCause());
                    }
                });
            }
        });
        // 
        manifest = obj.createAwmds();
        
        
    if(LocalDate.now().isAfter(LocalDate.of(2018, Month.DECEMBER, 31))){
            nouveau.setDisable(true);
            enregistrer.setDisable(true);
            importXML.setDisable(true);
            enregistrerXML.setDisable(true);
            client.setText("Date d'expiration atteinte...");
            (new AlertMsg()).alertMsg(AlertType.WARNING,"Attention", "Cette application a été expirée à la date du " + LocalDate.of(2016, Month.DECEMBER, 31).format(DateTimeFormatter.ofPattern("E dd MMM uuuu", Locale.FRENCH)), "Merci de contacter SEN PROG afin de renouveler.");
        }
        statusL.setText("Ready");
        statusL.setAlignment(Pos.CENTER_RIGHT);
        
        loadTree();
    }
    
    
    /*
     * En cliquant sur le bouton 'Enregistrer' cette methode est appelée.
     */
    private void saveManifest(ActionEvent event) throws NonexistentEntityException, IllegalOrphanException {
        awmds = obj.createAwmds();
        lineBol = 0;
        statusL.setText("traitement en cours");
        
        if(getManifestInfo(currentGeneralSegment)) {
            
            awmds.setGeneralSegment(currentGeneralSegment);
            item = makeBranch(customOfficeCode.getValue() + "-" + voyageNumber.getText(), tree2.getRoot());
            manifest = awmds;
            if(currentGeneralSegment.getTotalsSegment().getTotalNumberOfBols() > 0){
                lineBol++;
                focusTab("Information", "Traitement terminé.\nVoulez-vous ajouter un Titre de transport?", bol, enregistrer);                
                statusL.setText("Terminé.");
            }else{
                (new AlertMsg()).alertMsg(AlertType.WARNING, "WARNING", "Le nombre de Titre de transport de ce manifest est égal à 0.", "Cliquer sur 'Nouveau' pour créer un nouveau manifest.");
                statusL.setText("Terminé.");
            }
        }        
        loadTree();
    }
    
    private void saveBol(ActionEvent event){
        Awmds.BolSegment ab = obj.createAwmdsBolSegment();
        currentBol = getBol(ab);
        awmds.getBolSegment().add(currentBol);
        item.setExpanded(true);
        bolTree = makeBranch(currentBol.getBolId().getBolReference(), item);
        if(currentBol.getCtnSegment().size() < currentBol.getGoodsSegment().getNumOfCtnForThisBol()){
            focusTab("Ajout de conteneurs au bol", currentBol.getCtnSegment().size() + " Conteneurs enregistré(s) sur " + currentBol.getGoodsSegment().getNumOfCtnForThisBol() + ".\n Cliquer 'OK' pour ajouter un conteneur.", ctn, enregistrer);
        }else{
            focusTab("Information", currentBol.getCtnSegment().size() + " Conteneurs enregistré(s) sur " + currentBol.getGoodsSegment().getNumOfCtnForThisBol() + ".", bol, enregistrer);
            
            if(awmds.getBolSegment().size() < awmds.getGeneralSegment().getTotalsSegment().getTotalNumberOfBols()){
                focusTab("Ajout du Titre de transport", awmds.getBolSegment().size() + " Titre(s) de transport  enregistré(s) sur " + awmds.getGeneralSegment().getTotalsSegment().getTotalNumberOfBols() + ".", bol, enregistrer);
            }else{
                focusTab("Information", awmds.getBolSegment().size() + " Titre(s) de transport  enregistré(s) sur " + awmds.getGeneralSegment().getTotalsSegment().getTotalNumberOfBols() + ".\n Cliquer OK puis le button Enregistrer XML.", manifestTab, enregistrer);
            }
            
        }
        System.out.println(bolAnchor.getChildren().size());
        System.out.println("Titre de transport N°" + currentBol.getBolId().getLineNumber().getValue() + " ref " + blRefNbr.getText() + " a été ajouté...");
        loadTree();
        //clear(bolAnchor);
        manifest.getBolSegment().addAll(awmds.getBolSegment());        
    }
    
    
    private void saveCtn(ActionEvent event) throws SQLException, ClassNotFoundException{
        if(currentBol.getCtnSegment().size() < currentBol.getGoodsSegment().getNumOfCtnForThisBol()){
            Awmds.BolSegment.CtnSegment ctnr = obj.createAwmdsBolSegmentCtnSegment();
            getCtn(ctnr);
            currentBol.getCtnSegment().add(ctnr);
            lineCtn++;
//            ctnTable();
            TreeItem<String> item = makeBranch(ctnr.getCtnReference(), bolTree);
//            HashMap<TreeItem,Awmds.BolSegment.CtnSegment> abst = new HashMap<>();
//                    abst.put(item, ctnr);
//                    obs3.add(abst);
            System.out.println(" bol N° " + currentBol.getBolId().getLineNumber().getValue() + "a "+ currentBol.getCtnSegment().size() + "Conteneurs");
            if(currentBol.getCtnSegment().size() < currentBol.getGoodsSegment().getNumOfCtnForThisBol()){
                focusTab("Ajout de conteneurs au bol", currentBol.getCtnSegment().size() + " Conteneurs enregistré(s) sur " + currentBol.getGoodsSegment().getNumOfCtnForThisBol() + ".\nCliquer sur 'OK' pour ajouter un autre conteneur.", ctn, enregistrer);
            }else{
                focusTab("Information", currentBol.getCtnSegment().size() + " Conteneurs enregistré(s) sur " + currentBol.getGoodsSegment().getNumOfCtnForThisBol() + ".\nLe nombre MAX atteint, cliquer 'OK' pour continuer.", ctn, enregistrer);

                if(awmds.getBolSegment().size() < awmds.getGeneralSegment().getTotalsSegment().getTotalNumberOfBols()){
                    focusTab("Ajout du Titre de transport", awmds.getBolSegment().size() + " Titre(s) de transport enregistré(s) sur " + awmds.getGeneralSegment().getTotalsSegment().getTotalNumberOfBols() + ".\nCliquer 'OK' pour inserer un autre Titre de transport.", bol, enregistrer);
                }else{
                    focusTab("Information", awmds.getBolSegment().size() + " Titre(s) de transport enregistré(s) sur " + awmds.getGeneralSegment().getTotalsSegment().getTotalNumberOfBols() + ".\nCliquer OK puis le button Enregistrer XML.", manifestTab, enregistrer);
                }

            }
            
            clear(ctnAnchor);
            if(currentBol.getCtnSegment().size() == currentBol.getGoodsSegment().getNumOfCtnForThisBol()){
                //awmds.getBolSegment().add(currentBol);
//                Functions.AwmdsToXml(awmds, file);
            }
        }else{
            focusTab("Attention", "Nombre de conteneurs MAX atteint de " + (currentBol.getCtnSegment().size()) + "\n Voulez-vous ajouter un Titre de transport de plus?", bol, enregistrer);
            clear(ctnAnchor);            
        }
        manifest.getBolSegment().clear();
        manifest.getBolSegment().addAll(awmds.getBolSegment());
    }
    
    @FXML
    private void saveXML(ActionEvent event){
        System.out.println(" Extraction du manifest en fichier XML en cours...");
        FileChooser file2 = new FileChooser();
        FileChooser.ExtensionFilter xml = new FileChooser.ExtensionFilter("Cargo Manifest *.xml", "*.xml");
        file2.getExtensionFilters().add(xml);
        file2.setTitle("Extraction du manifest au format Sydonia");
        File f = file2.showSaveDialog(Manifest.primaryStage);
        
        try {
            if(f != null){
                FileWriter fileWriter = null;
                fileWriter = new FileWriter(f);
                fileWriter.close();
                Functions.AwmdsToXml(awmds, f);
                System.out.println("Fichier " + f.getName() + " extrait avec succès! ");
            }else{
                (new AlertMsg()).alertMsg(AlertType.WARNING, "Avertissement", "Pas de fichier trouvé", "Veuillez spécifier le fichier.\n Cliquer 'Annuler' puis 'Enregistrer XML'.");
            }
        } catch (IOException ex) {
            Logger.getLogger(manifestController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    private int indexOfItem(TreeItem item){
        return item.getParent().getChildren().indexOf(item); 
    }
    
    @FXML
    private void uploadXml(ActionEvent event) throws Exception {
        FileChooser.ExtensionFilter xml = new FileChooser.ExtensionFilter("Manifest Sydonia XML", "*.xml");
        FileChooser choix = new FileChooser();
        choix.getExtensionFilters().add(xml);
        choix.setTitle("Sélectionner un manifest Sydonia");
        File xmlFile = choix.showOpenDialog(Manifest.primaryStage);
        if(xmlFile != null){
            manifest = Functions.selectXml(xmlFile);
            (new AlertMsg()).alertMsg(AlertType.INFORMATION, "Importation du manifest XML", "Manifest importé avec SUCCÈS", "VOUS POUVEZ MODIFIER TOUTE SORTE D'INFORMATION");
            System.out.println("Manifest importé avec succes");
            fillManifestTab(manifest.getGeneralSegment());
            //Functions.persistToDB(manifest);
            item = makeBranch(xmlFile.getName(), tree2.getRoot());
            HashMap<TreeItem,Awmds.GeneralSegment> gs = new HashMap<>();
            gs.put(item, manifest.getGeneralSegment());
            obs1.add(gs);
            manifest.getBolSegment().forEach(bol ->{
                statusR.setText(bol.getBolId().getBolReference());
                TreeItem<String> item2 = makeBranch(bol.getBolId().getBolReference(), item);
                HashMap<TreeItem,Awmds.BolSegment> abs = new HashMap<>();
                abs.put(item2, bol);
                obs2.add(abs);
                bol.getCtnSegment().forEach(ctn -> {
                    statusR.setText(ctn.getCtnReference());
                    TreeItem<String> item3 = makeBranch(ctn.getCtnReference(), item2);
                    HashMap<TreeItem,Awmds.BolSegment.CtnSegment> abst = new HashMap<>();
                    abst.put(item3, ctn);
                    obs3.add(abst);
                });
            });
        }
    }
    
    private void fillManifestTab(Awmds.GeneralSegment ag){
        tabPane.getSelectionModel().select(manifestTab);
        try{
        this.customOfficeCode.setValue(ag.getGeneralSegmentId().getCustomsOfficeCode());
        this.voyageNumber.setText(ag.getGeneralSegmentId().getVoyageNumber());
        this.departureDate.setValue(LocalDate.parse(ag.getGeneralSegmentId().getDateOfDeparture()));
        this.arrivalDate.setValue(LocalDate.parse(ag.getGeneralSegmentId().getDateOfArrival()));
        this.arrivalTime.setText(ag.getGeneralSegmentId().getTimeOfArrival());
        
        this.departurePlaceCode.setValue(ag.getLoadUnloadPlace().getPlaceOfDepartureCode());
        this.destPlaceCode.setValue(ag.getLoadUnloadPlace().getPlaceOfDestinationCode());
        
        this.tonnageGross.setText(String.valueOf(ag.getTonnage().getTonnageGrossWeight()));
        this.tonnageNet.setText(String.valueOf(ag.getTonnage().getTonnageNetWeight()));
        
        Field[] agti2 = ag.getTransportInformation().getClass().getFields();
        for(int i = 0; i < agti2.length; i++){
            if(agti2[i].getName() == "shippingAgentCode"){
                this.shippingAgentCode.setText(ag.getTransportInformation().getShippingAgent().getShippingAgentCode());
                this.shippingAgentName.setText(ag.getTransportInformation().getShippingAgent().getShippingAgentName());
            }else{
                System.out.println("shippingAgentCode n'est pas défini");
            }
        }
        
        this.carrierCode.setText(ag.getTransportInformation().getCarrier().getCarrierCode());
        this.carrierName.setText(ag.getTransportInformation().getCarrier().getCarrierName());
        this.carrierAdress.setText(ag.getTransportInformation().getCarrier().getCarrierAddress());
        
        this.totalNbrBol.setText(String.valueOf(ag.getTotalsSegment().getTotalNumberOfBols()));
        this.totalNbrPkg.setText(String.valueOf(ag.getTotalsSegment().getTotalNumberOfPackages()));
        this.totalNbrCtn.setText(String.valueOf(ag.getTotalsSegment().getTotalNumberOfContainers()));
        this.totalGrossMass.setText(String.valueOf(ag.getTotalsSegment().getTotalGrossMass()));
        
        this.transportModeCode.setValue(ag.getTransportInformation().getModeOfTransportCode());
        this.transportNationalityCode.setValue(ag.getTransportInformation().getNationalityOfTransporterCode());
        this.transportRegistration.setText(ag.getTransportInformation().getRegistrationNumberOfTransportCode());
        this.transportMaster.setText(ag.getTransportInformation().getMasterInformation());
        this.transportIdentity.setText(ag.getTransportInformation().getIdentityOfTransporter());
        this.transportPlace.setText(ag.getTransportInformation().getPlaceOfTransporter());
        this.trasnportDate.setValue(LocalDate.parse(ag.getTransportInformation().getDateOfRegistration()));
        }catch(NullPointerException e){
            System.err.println(e.getLocalizedMessage());
            (new AlertMsg()).alertMsg(AlertType.ERROR, "Variable vide", "Il y a un champ qui est vide", e.getLocalizedMessage());
        }
    }
    
    private void fillBolTab(Awmds.BolSegment ab){
        tabPane.getSelectionModel().select(bol);
        this.blRefNbr.setText(ab.getBolId().getBolReference());
        this.blNature.setValue(ab.getBolId().getBolNature());
        this.blTypeCode.setValue(ab.getBolId().getBolTypeCode());
        this.blNbr1.setText(String.valueOf(ab.getBolId().getLineNumber().getValue()));
        
        
        this.blPlaceLoadCode.setValue(ab.getLoadUnloadPlace().getPlaceOfLoadingCode());
        this.blPlaceUnloadCode.setValue(ab.getLoadUnloadPlace().getPlaceOfUnloadingCode());
        
        this.blexporterCode.setText(ab.getTradersSegment().getExporter().getExporterCode());
        this.blexporterName.setText(ab.getTradersSegment().getExporter().getExporterName());
        this.blexporterAddress.setText(ab.getTradersSegment().getExporter().getExporterAddress());
        
        this.blnotifyCode.setText(ab.getTradersSegment().getNotify().getNotifyCode());
        this.blnotifyName.setText(ab.getTradersSegment().getNotify().getNotifyName());
        this.blnotifyAddress.setText(ab.getTradersSegment().getNotify().getNotifyAddress());
        
        this.blconsigneeCode.setText(ab.getTradersSegment().getConsignee().getConsigneeCode());
        this.blConsigneeName.setText(ab.getTradersSegment().getConsignee().getConsigneeName());
        this.blconsigneeAddress.setText(ab.getTradersSegment().getConsignee().getConsigneeAddress());
        
        this.blnbrCtn.setText(String.valueOf(ab.getGoodsSegment().getNumOfCtnForThisBol()));
        this.blnbrPkgs.setText(String.valueOf(ab.getGoodsSegment().getNumberOfPackages()));
        this.blpkgCode.setValue(ab.getGoodsSegment().getPackageTypeCode());
        this.blgrossMass.setText(String.valueOf(ab.getGoodsSegment().getGrossMass()));
        this.bldescGoods.setText(ab.getGoodsSegment().getGoodsDescription());
        this.blmarknb.setText(ab.getGoodsSegment().getShippingMarks());
        this.blvolumeCBM.setText(String.valueOf(ab.getGoodsSegment().getVolumeInCubicMeters()));
        this.blsealNbr.setText(String.valueOf(ab.getGoodsSegment().getSealsSegment().getNumberOfSeals()));
        this.blmarks.setText(ab.getGoodsSegment().getSealsSegment().getMarksOfSeals());
        this.blparty1.setText(ab.getGoodsSegment().getSealsSegment().getSealingPartyCode());
        this.info.setText(ab.getGoodsSegment().getInformation());
        
//        this.blpcInd.setText(ab.getValueSegment().getFreightSegment().getPCIndicator());
//        this.blamount.setText(String.valueOf(ab.getValueSegment().getFreightSegment().getFreightValue()));
//        this.blcurrency.setText(ab.getValueSegment().getFreightSegment().getFreightCurrency());
//        
//        this.blcustom1.setText(String.valueOf(ab.getValueSegment().getCustomsSegment().getCustomsValue()));
//        this.blcustom2.setText(ab.getValueSegment().getCustomsSegment().getCustomsCurrency());
//        
//        this.blinsurance1.setText(String.valueOf(ab.getValueSegment().getInsuranceSegment().getInsuranceValue()));
//        this.blinsurance2.setText(ab.getValueSegment().getInsuranceSegment().getInsuranceCurrency());
//        
//        this.bltransport1.setText(String.valueOf(ab.getValueSegment().getTransportSegment().getTransportValue()));
//        this.bltransport2.setText(ab.getValueSegment().getTransportSegment().getTransportCurrency());      
    }
    
    private void fillCtnTab(Awmds.BolSegment.CtnSegment abc){
        tabPane.getSelectionModel().select(ctn);
        this.ctnRef.setText(abc.getCtnReference());
        this.ctnNbr.setText(String.valueOf(abc.getNumberOfPackages()));
        this.ctnType.setValue(abc.getTypeOfContainer());
        this.ctnFcl.setValue(abc.getEmptyFull());
        this.ctnMarks1.setText(abc.getMarks1());
        this.ctnMarks2.setText(abc.getMarks2());
        this.ctnSealingParty.setValue(abc.getSealingParty());
        this.ctnEmptyWeight.setText(String.valueOf(abc.getEmptyWeight()));
        this.ctnGoodsWeight.setText(String.valueOf(abc.getGoodsWeight()));
    }
    /*
     * Methodes permettant de recuperer les infos sur les champs et copier dans une instance 
     * de AWMDS
    */
          
    //Creer un nouveau SegmentGeneral des champs de l'onglet manifest
    private boolean getManifestInfo(Awmds.GeneralSegment ag){
        //
        Awmds.GeneralSegment.GeneralSegmentId agi = obj.createAwmdsGeneralSegmentGeneralSegmentId();
        Awmds.GeneralSegment.TotalsSegment agt = obj.createAwmdsGeneralSegmentTotalsSegment();
        Awmds.GeneralSegment.LoadUnloadPlace agl = obj.createAwmdsGeneralSegmentLoadUnloadPlace();
        Awmds.GeneralSegment.Tonnage agto = obj.createAwmdsGeneralSegmentTonnage();
        Awmds.GeneralSegment.AttachedDocument aga = obj.createAwmdsGeneralSegmentAttachedDocument();
        Awmds.GeneralSegment.TransportInformation agti = obj.createAwmdsGeneralSegmentTransportInformation();
        Awmds.GeneralSegment.TransportInformation.Carrier agtic = obj.createAwmdsGeneralSegmentTransportInformationCarrier();
        Awmds.GeneralSegment.TransportInformation.ShippingAgent agtis = obj.createAwmdsGeneralSegmentTransportInformationShippingAgent();
        //
            if(customOfficeCode.getValue().isEmpty()){
                (new AlertMsg()).alertMsg(AlertType.INFORMATION, "Champ obligatoire", "Le code bureau doit être inséré obligatoirement!!!", "Merci de taper le CODE.");
                customOfficeCode.requestFocus();
                return false;
            }else{
                agi.setCustomsOfficeCode(customOfficeCode.getValue());
                if(voyageNumber.getText().isEmpty()){
                    (new AlertMsg()).alertMsg(AlertType.INFORMATION, "Champ obligatoire", "Le numero de voyage doit être inséré obligatoirement!!!", "Merci de taper ce numéro.");
                    voyageNumber.requestFocus();
                    return false;
                }else{
                    agi.setVoyageNumber(voyageNumber.getText());
                    if(departureDate.getValue() == null){
                        (new AlertMsg()).alertMsg(AlertType.INFORMATION, "Champ obligatoire", "La date de départ doit être insérée obligatoirement!!!", "Merci de selectionner cette date.");
                        departureDate.requestFocus();
                        return false;
                    }else{
                        agi.setDateOfDeparture(departureDate.getValue().toString());
                        if(arrivalDate.getValue() == null){
                            (new AlertMsg()).alertMsg(AlertType.INFORMATION, "Champ obligatoire", "La date d'arrivée doit être insérée obligatoirement!!!", "Merci de selectionner cette date.");
                            arrivalDate.requestFocus();
                            return false;
                        }else{
                            agi.setDateOfArrival(arrivalDate.getValue().toString());
                            if(arrivalTime.getText().isEmpty()){
                                (new AlertMsg()).alertMsg(AlertType.ERROR, "Champ obligatoire", "Heure d'arrivée doit être insérée", "Merci de tenir compte de cette exigence.");
                                arrivalTime.requestFocus();
                                return false;
                            }else{
                                agi.setTimeOfArrival(arrivalTime.getText());
                                if(destPlaceCode.getValue().isEmpty()){
                                        (new AlertMsg()).alertMsg(AlertType.INFORMATION, "Champ obligatoire", "La place de destination doit être insérée obligatoirement!!!", "Merci d'insérer le lieu.");
                                        destPlaceCode.requestFocus();
                                        return false;
                                }else{
                                        agl.setPlaceOfDestinationCode(destPlaceCode.getValue());
                                        if(departurePlaceCode.getValue().isEmpty()){
                                            (new AlertMsg()).alertMsg(AlertType.INFORMATION, "Champ obligatoire", "La place de départ doit être insérée obligatoirement!!!", "Merci d'insérer le lieu.");
                                        }else{
                                            agl.setPlaceOfDepartureCode(departurePlaceCode.getValue());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            
            if(!carrierCode.getText().isEmpty()){
                agtic.setCarrierCode(carrierCode.getText());
                agtic.setCarrierName(carrierName.getText());
                agtic.setCarrierAddress(carrierAdress.getText());
            }else{
                (new AlertMsg()).alertMsg(AlertType.ERROR, "Champ obligatoire", "code sydonia du transporteur", "Merci de d'insérer ce code.");
                carrierCode.requestFocus();
                return false;
            }
            
            if(transportModeCode.getValue() == null){
                (new AlertMsg()).alertMsg(AlertType.ERROR, "Champ obligatoire", "Champ mode de transport vide!", "Merci de d'ínsérer le mode");
                transportModeCode.requestFocus();
                return false;
            }else{
                    agti.setModeOfTransportCode(transportModeCode.getValue());
            }
            
            if(!transportIdentity.getText().isEmpty()){
                agti.setIdentityOfTransporter(transportIdentity.getText());
                agti.setMasterInformation(transportMaster.getText());
                agti.setNationalityOfTransporterCode(transportNationalityCode.getValue());
                agti.setPlaceOfTransporter(transportPlace.getText());
            }else{
                (new AlertMsg()).alertMsg(AlertType.ERROR, "Champ obligatoire", "Champ identité du Navire vide!", "Merci de d'ínsérer le nom du navire.");
                transportIdentity.requestFocus();
                return false;
            }
            
            if(!shippingAgentCode.getText().isEmpty()){
                agtis.setShippingAgentCode(shippingAgentCode.getText());
                agtis.setShippingAgentName(shippingAgentName.getText());
                agti.setShippingAgent(agtis);
            }else{
                agti.setShippingAgent(null);
            }
            
            if(!totalNbrBol.getText().isEmpty()){
                agt.setTotalNumberOfBols(Integer.valueOf(totalNbrBol.getText()));
            }else{
                (new AlertMsg()).alertMsg(AlertType.ERROR, "Champ obligatoire", "le nombre de BLs ne peut pas être vide!", "Merci de remplir.");
                totalNbrBol.requestFocus();
                return false;
            }
            if(!totalNbrCtn.getText().isEmpty()){
                agt.setTotalNumberOfContainers(Integer.valueOf(totalNbrCtn.getText()));
            }else{
                (new AlertMsg()).alertMsg(AlertType.ERROR, "Champ obligatoire", "le nombre de conteneurs ne peut pas être vide!", "Merci de remplir.");
                totalNbrCtn.requestFocus();
                return false;
            }
            if(!totalNbrPkg.getText().isEmpty()){
                agt.setTotalNumberOfPackages(Integer.valueOf(totalNbrPkg.getText()));
            }else{
                (new AlertMsg()).alertMsg(AlertType.ERROR, "Champ obligatoire", "le nombre de colis ne peut pas être vide!", "Merci de remplir.");
                totalNbrPkg.requestFocus();
                return false;
            }
            if(!totalGrossMass.getText().isEmpty()){
                agt.setTotalGrossMass(Integer.valueOf(totalGrossMass.getText()));
            }else{
                (new AlertMsg()).alertMsg(AlertType.ERROR, "Champ obligatoire", "le poids brut ne peut pas être vide!", "Merci de remplir.");
                totalGrossMass.requestFocus();
                return false;
            }
            
            if(!tonnageNet.getText().isEmpty())
                agto.setTonnageNetWeight(Double.valueOf(tonnageNet.getText()));
            if(!tonnageGross.getText().isEmpty())
                agto.setTonnageGrossWeight(Double.valueOf(tonnageGross.getText()));
            
            agti.setCarrier(agtic);
            ag.setGeneralSegmentId(agi);
            ag.setTotalsSegment(agt);
            ag.setLoadUnloadPlace(agl);
            ag.setTonnage(agto);
            ag.setTransportInformation(agti);            
            lineMan++;
            System.out.println(" manifest N° " + lineMan);
            return true;
    }
    
    //creer le BL numero #lineNumber a ajouter au manifest. 
    //Renvoie ce bl
    private Awmds.BolSegment getBol(Awmds.BolSegment ab){
        Awmds.BolSegment.BolId abbi = obj.createAwmdsBolSegmentBolId();
        abbi.setLineNumber(obj.createAwmdsBolSegmentBolIdLineNumber());
        Awmds.BolSegment.GoodsSegment abgs = obj.createAwmdsBolSegmentGoodsSegment();
        Awmds.BolSegment.LoadUnloadPlace ablup = obj.createAwmdsBolSegmentLoadUnloadPlace();
        Awmds.BolSegment.Location abl = obj.createAwmdsBolSegmentLocation();
        Awmds.BolSegment.SplitSegment abss = obj.createAwmdsBolSegmentSplitSegment();
        Awmds.BolSegment.TradersSegment abts = obj.createAwmdsBolSegmentTradersSegment();
        abts.setConsignee(obj.createAwmdsBolSegmentTradersSegmentConsignee());
        abts.setExporter(obj.createAwmdsBolSegmentTradersSegmentExporter());
        abts.setNotify(obj.createAwmdsBolSegmentTradersSegmentNotify());
        Awmds.BolSegment.ValueSegment abvs = obj.createAwmdsBolSegmentValueSegment();
        
        if(this.blNature.getValue() == null){
                (new AlertMsg()).alertMsg(AlertType.ERROR, "Champ obligatoire", "Champ 'Nature' est vide", "Merci de remplir.");
                this.blNature.requestFocus();
            }else{
                abbi.setBolNature((String) this.blNature.getValue());
        }
        abbi.setBolReference(this.blRefNbr.getText());
        abbi.setBolTypeCode((String) this.blTypeCode.getValue());
        abbi.getLineNumber().setValue(Integer.valueOf(this.blNbr1.getText()));
        
        ab.setBolId(abbi);
        
        //
        abgs.setGoodsDescription(this.bldescGoods.getText());
        abgs.setGrossMass(Double.valueOf(this.blgrossMass.getText()));
        abgs.setInformation(this.info.getText());
        abgs.setNumOfCtnForThisBol(Integer.valueOf(this.blnbrCtn.getText()));
        abgs.setNumberOfPackages(Double.valueOf(this.blnbrPkgs.getText()).intValue());
        abgs.setPackageTypeCode(this.blpkgCode.getValue());
        abgs.setShippingMarks(this.blmarknb.getText());
        if(!this.blvolumeCBM.getText().isEmpty()){
            abgs.setVolumeInCubicMeters(Double.valueOf(this.blvolumeCBM.getText()));
        }else{
            abgs.setVolumeInCubicMeters(0.0);
        }
        
        abgs.setSealsSegment(null);
//        abgs.getSealsSegment().setMarksOfSeals(this.blmarks.getText());
//        if(!this.blsealNbr.getText().isEmpty())
//            abgs.getSealsSegment().setNumberOfSeals(Integer.valueOf(this.blsealNbr.getText()));
//        
//        abgs.getSealsSegment().setSealingPartyCode(this.blparty1.getText());
        
        ablup.setPlaceOfLoadingCode(this.blPlaceLoadCode.getValue());
        ablup.setPlaceOfUnloadingCode(this.blPlaceUnloadCode.getValue());
        
        abl.setLocationCode(this.blportCode.getValue());
        abl.setLocationInfo(this.blportName.getText());
        
        abts.getConsignee().setConsigneeCode(this.blconsigneeCode.getText());
        abts.getConsignee().setConsigneeName(this.blConsigneeName.getText());
        abts.getConsignee().setConsigneeAddress(this.blconsigneeAddress.getText());
        
        abts.getExporter().setExporterCode(this.blexporterCode.getText());
        abts.getExporter().setExporterName(this.blexporterName.getText());
        abts.getExporter().setExporterAddress(this.blexporterAddress.getText());
        
        abts.getNotify().setNotifyCode(this.blnotifyCode.getText());
        abts.getNotify().setNotifyName(this.blnotifyName.getText());
        abts.getNotify().setNotifyAddress(this.blnotifyAddress.getText());
        
        if(!this.blcustom1.getText().isEmpty()){
        abvs.setCustomsSegment(obj.createAwmdsBolSegmentValueSegmentCustomsSegment());
        abvs.getCustomsSegment().setCustomsValue(Double.valueOf(this.blcustom1.getText()));
        abvs.getCustomsSegment().setCustomsCurrency(this.blcustom2.getText());
        }
        if(!this.blpcInd.getText().isEmpty()){
        abvs.setFreightSegment(obj.createAwmdsBolSegmentValueSegmentFreightSegment());
        abvs.getFreightSegment().setPCIndicator(this.blpcInd.getText());
        abvs.getFreightSegment().setFreightValue(Double.valueOf(this.blamount.getText()));
        abvs.getFreightSegment().setFreightCurrency(this.blcurrency.getText());
        }
        if(!this.blinsurance1.getText().isEmpty()){
        abvs.setInsuranceSegment(obj.createAwmdsBolSegmentValueSegmentInsuranceSegment());
        abvs.getInsuranceSegment().setInsuranceValue(Double.valueOf(this.blinsurance1.getText()));
        abvs.getInsuranceSegment().setInsuranceCurrency(this.blinsurance2.getText());
        }
        if(!this.bltransport1.getText().isEmpty()){
        abvs.setTransportSegment(obj.createAwmdsBolSegmentValueSegmentTransportSegment());
        abvs.getTransportSegment().setTransportValue(Double.valueOf(this.bltransport1.getText()));
        abvs.getTransportSegment().setTransportCurrency(this.bltransport2.getText());
        }
        
        ab.setBolId(abbi);
        ab.setGoodsSegment(abgs);
        ab.setLoadUnloadPlace(ablup);
        ab.setLocation(abl);
        ab.setTradersSegment(abts);
        ab.setValueSegment(abvs);
        
        System.out.println("Bol chargé "+ ab.getBolId().getBolReference());
        return ab;
    }
    
    //creer le container au bol
    private void getCtn(Awmds.BolSegment.CtnSegment abc){
        
        abc.setCtnReference(this.ctnRef.getText());
        if(!this.ctnEmptyWeight.getText().isEmpty())
            abc.setEmptyWeight(Double.valueOf(this.ctnEmptyWeight.getText()));
        
        abc.setEmptyFull(this.ctnFcl.getValue());
        
        if(!this.ctnGoodsWeight.getText().isEmpty())
            abc.setGoodsWeight(Double.valueOf(this.ctnGoodsWeight.getText()));
        
        abc.setMarks1(this.ctnSeals.getText());
        abc.setMarks2(this.ctnMarks1.getText());
        abc.setMarks3(this.ctnMarks2.getText());
        
        abc.setNumberOfPackages(Integer.valueOf(this.ctnNbr.getText()));
        abc.setSealingParty(this.ctnSealingParty.getValue());
        abc.setTypeOfContainer(this.ctnType.getValue());
        
    }
    
    //methode permettant de recharger la liste a gauche de manifest et bols déjà enregistré
    public void loadTree(){
        cargo.setExpanded(true);
        tree2.setRoot(cargo);
        tree2.setShowRoot(true);
    }
    
    //permet de remplir les champs depuis d'un objet Awmds
    private void loadManifest(){
        customOfficeCode.setValue(manifest.getGeneralSegment().getGeneralSegmentId().getCustomsOfficeCode());
        
        
    }
    
    //Assure l'activation de l'autocompletion sur la liste de code a selectionner afin filtrer et n'affiche que ce qui correspond aux lettres tapées
    private void loadComboBox(ComboBox<String> combo, TextField tf, LinkedHashMap list, int nb){
        
        combo.getItems().addAll(list.keySet());
        combo.valueProperty().addListener((ObservableValue<? extends String> arg0, String arg1, String arg2) -> {
            try{
                combo.setValue(arg2.substring(0, nb));
                tf.setText((String) list.get(arg2));
            }catch(IndexOutOfBoundsException e){
                System.out.println("No problem");
            }
        });
    }
    
    //Ajout un objet dans un tree
    private TreeItem<String> makeBranch(String cargo, TreeItem<String> parent){
        
        TreeItem<String> item1 = new TreeItem<>(cargo);
        item1.setExpanded(true);
        parent.getChildren().add(item1);
        return item1;
    }
    
    //changer l'onglet pour se focaliser une autre chose
    public String focusTab(String title, String msg, Tab tab, Button but){
//        TrayNotification notif = new TrayNotification(title, msg, Notifications.NOTICE);
        AlertMsg notif = new AlertMsg();
        String result = notif.alertMsg(AlertType.INFORMATION, title, "INFORMATION", msg);
        
        switch(result){
            case "OK":
                tabPane.getSelectionModel().select(tab);
                tab.disableProperty().set(false);
                if(tab == bol){                    
                    if(Integer.valueOf(totalNbrBol.getText()) > 0 && ( awmds.getBolSegment().isEmpty() || awmds.getBolSegment().size() < awmds.getGeneralSegment().getTotalsSegment().getTotalNumberOfBols())){                        
                        bol.setDisable(false);
                        manifestTab.setDisable(true);
                        ctn.setDisable(true);
                        but.setOnAction(eBol -> saveBol(eBol));
                        //but.setText("Enregistrer le B/L");
                        but.setFont(Font.font(8));
                        blNbr1.setText(String.valueOf(lineBol));
                        clear(manifestArea);
        //                    saveManifest.setVisible(false);
        //                    saveManifest.setVisible(false);
                    }else{
                        if(focusTab("Info", "Manifest N° " + lineMan + " est terminé.\n Cliquer OK pour l'enregistrer.\n", manifestTab, enregistrer) == "OK")
                            saveXML(new ActionEvent());
                    }
                }
                if(tab == ctn){
                    ctn.setDisable(false);
                    bol.setDisable(true);
                    manifestTab.setDisable(true);
                    //but.setText("Ajouter le conteneur");
                    blPlaceLoadCode.setValue(departurePlaceCode.getValue());
                    blPlaceUnloadCode.setValue(destPlaceCode.getValue());
                    but.setOnAction(eCtn -> {
                        try {
                            saveCtn(eCtn);
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(manifestController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                }
                if(tab == manifestTab){
                    manifestTab.setDisable(false);
                    ctn.setDisable(true);
                    bol.setDisable(true);
                    //but.setText("Enregistrer Manifest");
                     but.setOnAction(eCtn -> {
                        
                        try {
                            saveManifest(eCtn);
                        } catch (NonexistentEntityException | IllegalOrphanException ex) {
                            Logger.getLogger(manifestController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    });
                }
            case "Annuler":
        }
        return result;
    }
    
    //Clear all the field
    private void clear(Node node){

        if(node instanceof TextField){
            ((TextField) node).clear();
            System.out.println(node.getId() + "is cleared!");
        }
        if(node instanceof TextArea){
            ((TextArea) node).clear();
            System.out.println(node.getId() + "is cleared!");
        }
        if(node instanceof ComboBox){
            ((ComboBox) node).setValue(null);
            System.out.println(node.getId() + "is cleared!");
        }
        if(node instanceof DatePicker){
            ((DatePicker) node).setValue(null);
            System.out.println(node.getId() + "is cleared!");
        }
        if(node instanceof VBox){
            ((VBox) node).getChildren().forEach(it -> clear(it));
        }
        if(node instanceof HBox){
            ((HBox) node).getChildren().forEach(it -> clear(it));
        }
        if(node instanceof AnchorPane){
            ((AnchorPane) node).getChildren().forEach(it -> clear(it));
        }
        if(node instanceof ScrollPane){
            ((ScrollPane) node).getChildrenUnmodifiable().forEach(it -> clear(it));
        }
    }
    
    private ObservableList<Awmds.BolSegment.CtnSegment> getCtn(Awmds.BolSegment bol){
        ObservableList<Awmds.BolSegment.CtnSegment> ctnrs = FXCollections.observableArrayList();
        
        ctnrs.addAll(bol.getCtnSegment());
        
        return ctnrs;
    }
    private void ctnTable() throws ClassNotFoundException, SQLException{
        refCtn.setCellValueFactory(new PropertyValueFactory<>("CtnReference"));
        nbrPkgCtn.setCellValueFactory(new PropertyValueFactory<>("numberOfPackages"));
        typeCtn.setCellValueFactory(new PropertyValueFactory<>("typeOfContainer"));
        EFCtn.setCellValueFactory(new PropertyValueFactory<>("emptyFull"));
        marks1Ctn.setCellValueFactory(new PropertyValueFactory<>("marks1"));
        marks2Ctn.setCellValueFactory(new PropertyValueFactory<>("marks2"));
        sealPartyCtn.setCellValueFactory(new PropertyValueFactory<>("sealingParty"));
        emptyCtn.setCellValueFactory(new PropertyValueFactory<>("emptyWeight"));
        goodsCtn.setCellValueFactory(new PropertyValueFactory<>("goodsWeight"));
        
        BillOfLandingJpaController bljc = new BillOfLandingJpaController(Functions.EMF);
        int lastBol = bljc.findLastId();
        EntityManager em = Functions.EMF.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Container> q = cb.createQuery(Container.class);
        Root<Container> c = q.from(Container.class);
        ParameterExpression<Integer> p = cb.parameter(Integer.class);
        q.select(c).where(cb.equal(c.get("idBol"), p));
        
        TypedQuery<Container> query = em.createQuery(q);
        query.setParameter(p, Integer.valueOf(lastBol));
        query.getResultList().forEach(e -> System.out.println(e.getIdCtn()));
        data.addAll(query.getResultList());
        
        ctnTable.setItems(data);
    }

    private void setContextMenu(Control control){
        final ContextMenu contextMenu = new ContextMenu();
        contextMenu.setOnShowing((WindowEvent e) -> {
            System.out.println("showing");
        });
        contextMenu.setOnShown((WindowEvent e) -> {
            System.out.println("shown");
        });
        MenuItem item1 = new MenuItem("Ouvrir");
        item1.setOnAction((ActionEvent e) -> {
           TreeItem treeItem = tree2.getSelectionModel().getSelectedItem();
           int index = tree2.getSelectionModel().getSelectedIndex();
            System.out.println("index " + index);
           if(treeItem.getParent() == item){
               int bl = item.getChildren().indexOf(treeItem);
               
               System.out.println("index bol " + bl);
               this.bol.setDisable(false);
               fillBolTab(awmds.getBolSegment().get(bl));
               enregistrer.setText("Enregistrer");
               enregistrer.setOnAction(et -> {
                   System.out.println("Nombre de B/L : " + awmds.getBolSegment().size());
                   Awmds.BolSegment bol = getBol(awmds.getBolSegment().remove(bl));
                    awmds.getBolSegment().add(bl, bol);
                    treeItem.setValue(blRefNbr.getText());
                    if(bol.getCtnSegment().size() < bol.getGoodsSegment().getNumOfCtnForThisBol()){
                        focusTab("Ajout de conteneurs au bol", bol.getCtnSegment().size() + " Conteneurs enregistré(s) sur " + bol.getGoodsSegment().getNumOfCtnForThisBol() + ".\n Cliquer 'OK' pour ajouter un conteneur.", ctn, enregistrer);
                    }else{
                        focusTab("Information", bol.getCtnSegment().size() + " Conteneurs enregistré(s) sur " + bol.getGoodsSegment().getNumOfCtnForThisBol() + ".", this.bol, enregistrer);

                        if(awmds.getBolSegment().size() < awmds.getGeneralSegment().getTotalsSegment().getTotalNumberOfBols()){
                            focusTab("Ajout du Titre de transport", awmds.getBolSegment().size() + " Titre(s) de transport  enregistré(s) sur " + awmds.getGeneralSegment().getTotalsSegment().getTotalNumberOfBols() + ".", this.bol, enregistrer);
                        }else{
                            focusTab("Information", awmds.getBolSegment().size() + " Titre(s) de transport  enregistré(s) sur " + awmds.getGeneralSegment().getTotalsSegment().getTotalNumberOfBols() + ".\n Cliquer OK puis le button Enregistrer XML.", manifestTab, enregistrer);
                        }

                    }
                    System.out.println("B/L numero" + awmds.getBolSegment().get(bl).getBolId().getLineNumber().getValue() + " a pour ref. " + awmds.getBolSegment().get(bl).getBolId().getBolReference());
               });
               System.out.println("Nombre de B/L : " + awmds.getBolSegment().size());
               //System.out.println("B/L numero" + awmds.getBolSegment().get(bl).getBolId().getLineNumber().getValue() + " a pour ref. " + awmds.getBolSegment().get(bl).getBolId().getBolReference());
           }
           System.out.println(treeItem + " a pour index " + index);
           
                      
        });
        MenuItem item2 = new MenuItem("Preferences");
        item2.setOnAction((ActionEvent e) -> {
            System.out.println("Preferences");
        });
        contextMenu.getItems().add(item1);
        control.setContextMenu(contextMenu);
    }
    
}
