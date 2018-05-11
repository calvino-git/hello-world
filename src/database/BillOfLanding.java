/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author itsystem
 */
@Entity
@Table(name = "BILL_OF_LANDING", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BillOfLanding.findAll", query = "SELECT b FROM BillOfLanding b"),
    @NamedQuery(name = "BillOfLanding.findByIdBol", query = "SELECT b FROM BillOfLanding b WHERE b.idBol = :idBol"),
    @NamedQuery(name = "BillOfLanding.findByBolreference", query = "SELECT b FROM BillOfLanding b WHERE b.bolreference = :bolreference"),
    @NamedQuery(name = "BillOfLanding.findByLinenumber", query = "SELECT b FROM BillOfLanding b WHERE b.linenumber = :linenumber"),
    @NamedQuery(name = "BillOfLanding.findByBolnature", query = "SELECT b FROM BillOfLanding b WHERE b.bolnature = :bolnature"),
    @NamedQuery(name = "BillOfLanding.findByBoltypecode", query = "SELECT b FROM BillOfLanding b WHERE b.boltypecode = :boltypecode"),
    @NamedQuery(name = "BillOfLanding.findByMasterbolrefnumber", query = "SELECT b FROM BillOfLanding b WHERE b.masterbolrefnumber = :masterbolrefnumber"),
    @NamedQuery(name = "BillOfLanding.findByUniquecarrierreference", query = "SELECT b FROM BillOfLanding b WHERE b.uniquecarrierreference = :uniquecarrierreference"),
    @NamedQuery(name = "BillOfLanding.findByPlaceofloadingcode", query = "SELECT b FROM BillOfLanding b WHERE b.placeofloadingcode = :placeofloadingcode"),
    @NamedQuery(name = "BillOfLanding.findByPlaceofunloadingcode", query = "SELECT b FROM BillOfLanding b WHERE b.placeofunloadingcode = :placeofunloadingcode"),
    @NamedQuery(name = "BillOfLanding.findByExportercode", query = "SELECT b FROM BillOfLanding b WHERE b.exportercode = :exportercode"),
    @NamedQuery(name = "BillOfLanding.findByExportername", query = "SELECT b FROM BillOfLanding b WHERE b.exportername = :exportername"),
    @NamedQuery(name = "BillOfLanding.findByExporteraddress", query = "SELECT b FROM BillOfLanding b WHERE b.exporteraddress = :exporteraddress"),
    @NamedQuery(name = "BillOfLanding.findByNotifycode", query = "SELECT b FROM BillOfLanding b WHERE b.notifycode = :notifycode"),
    @NamedQuery(name = "BillOfLanding.findByNotifyname", query = "SELECT b FROM BillOfLanding b WHERE b.notifyname = :notifyname"),
    @NamedQuery(name = "BillOfLanding.findByNotifyaddress", query = "SELECT b FROM BillOfLanding b WHERE b.notifyaddress = :notifyaddress"),
    @NamedQuery(name = "BillOfLanding.findByConsigneecode", query = "SELECT b FROM BillOfLanding b WHERE b.consigneecode = :consigneecode"),
    @NamedQuery(name = "BillOfLanding.findByConsigneename", query = "SELECT b FROM BillOfLanding b WHERE b.consigneename = :consigneename"),
    @NamedQuery(name = "BillOfLanding.findByConsigneeaddress", query = "SELECT b FROM BillOfLanding b WHERE b.consigneeaddress = :consigneeaddress"),
    @NamedQuery(name = "BillOfLanding.findByNumberofpackages", query = "SELECT b FROM BillOfLanding b WHERE b.numberofpackages = :numberofpackages"),
    @NamedQuery(name = "BillOfLanding.findByPackagetypecode", query = "SELECT b FROM BillOfLanding b WHERE b.packagetypecode = :packagetypecode"),
    @NamedQuery(name = "BillOfLanding.findByGrossmass", query = "SELECT b FROM BillOfLanding b WHERE b.grossmass = :grossmass"),
    @NamedQuery(name = "BillOfLanding.findByShippingmarks", query = "SELECT b FROM BillOfLanding b WHERE b.shippingmarks = :shippingmarks"),
    @NamedQuery(name = "BillOfLanding.findByGoodsdescription", query = "SELECT b FROM BillOfLanding b WHERE b.goodsdescription = :goodsdescription"),
    @NamedQuery(name = "BillOfLanding.findByVolumeincubicmeters", query = "SELECT b FROM BillOfLanding b WHERE b.volumeincubicmeters = :volumeincubicmeters"),
    @NamedQuery(name = "BillOfLanding.findByNumofctnforthisbol", query = "SELECT b FROM BillOfLanding b WHERE b.numofctnforthisbol = :numofctnforthisbol"),
    @NamedQuery(name = "BillOfLanding.findByInformation", query = "SELECT b FROM BillOfLanding b WHERE b.information = :information"),
    @NamedQuery(name = "BillOfLanding.findByPCindicator", query = "SELECT b FROM BillOfLanding b WHERE b.pCindicator = :pCindicator"),
    @NamedQuery(name = "BillOfLanding.findByFreightvalue", query = "SELECT b FROM BillOfLanding b WHERE b.freightvalue = :freightvalue"),
    @NamedQuery(name = "BillOfLanding.findByFreightcurrency", query = "SELECT b FROM BillOfLanding b WHERE b.freightcurrency = :freightcurrency"),
    @NamedQuery(name = "BillOfLanding.findByCustomsvalue", query = "SELECT b FROM BillOfLanding b WHERE b.customsvalue = :customsvalue"),
    @NamedQuery(name = "BillOfLanding.findByCustomscurrency", query = "SELECT b FROM BillOfLanding b WHERE b.customscurrency = :customscurrency"),
    @NamedQuery(name = "BillOfLanding.findByInsurancevalue", query = "SELECT b FROM BillOfLanding b WHERE b.insurancevalue = :insurancevalue"),
    @NamedQuery(name = "BillOfLanding.findByInsurancecurrency", query = "SELECT b FROM BillOfLanding b WHERE b.insurancecurrency = :insurancecurrency"),
    @NamedQuery(name = "BillOfLanding.findByTransportvalue", query = "SELECT b FROM BillOfLanding b WHERE b.transportvalue = :transportvalue"),
    @NamedQuery(name = "BillOfLanding.findByTransportcurrency", query = "SELECT b FROM BillOfLanding b WHERE b.transportcurrency = :transportcurrency"),
    @NamedQuery(name = "BillOfLanding.findByLocationcode", query = "SELECT b FROM BillOfLanding b WHERE b.locationcode = :locationcode"),
    @NamedQuery(name = "BillOfLanding.findByLocationinfo", query = "SELECT b FROM BillOfLanding b WHERE b.locationinfo = :locationinfo")})
public class BillOfLanding implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_bol")
    private Integer idBol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "Bol_reference")
    private String bolreference;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "Line_number")
    private String linenumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "Bol_nature")
    private String bolnature;
    @Size(max = 2000000000)
    @Column(name = "Bol_type_code")
    private String boltypecode;
    @Size(max = 2000000000)
    @Column(name = "Master_bol_ref_number")
    private String masterbolrefnumber;
    @Size(max = 2000000000)
    @Column(name = "Unique_carrier_reference")
    private String uniquecarrierreference;
    @Size(max = 2000000000)
    @Column(name = "Place_of_loading_code")
    private String placeofloadingcode;
    @Size(max = 2000000000)
    @Column(name = "Place_of_unloading_code")
    private String placeofunloadingcode;
    @Size(max = 2000000000)
    @Column(name = "Exporter_code")
    private String exportercode;
    @Size(max = 2000000000)
    @Column(name = "Exporter_name")
    private String exportername;
    @Size(max = 2000000000)
    @Column(name = "Exporter_address")
    private String exporteraddress;
    @Size(max = 2000000000)
    @Column(name = "Notify_code")
    private String notifycode;
    @Size(max = 2000000000)
    @Column(name = "Notify_name")
    private String notifyname;
    @Size(max = 2000000000)
    @Column(name = "Notify_address")
    private String notifyaddress;
    @Size(max = 2000000000)
    @Column(name = "Consignee_code")
    private String consigneecode;
    @Size(max = 2000000000)
    @Column(name = "Consignee_name")
    private String consigneename;
    @Size(max = 2000000000)
    @Column(name = "Consignee_address")
    private String consigneeaddress;
    @Size(max = 2000000000)
    @Column(name = "Number_of_packages")
    private String numberofpackages;
    @Size(max = 2000000000)
    @Column(name = "Package_type_code")
    private String packagetypecode;
    @Size(max = 2000000000)
    @Column(name = "Gross_mass")
    private String grossmass;
    @Size(max = 2000000000)
    @Column(name = "Shipping_marks")
    private String shippingmarks;
    @Size(max = 2000000000)
    @Column(name = "Goods_description")
    private String goodsdescription;
    @Size(max = 2000000000)
    @Column(name = "Volume_in_cubic_meters")
    private String volumeincubicmeters;
    @Size(max = 2000000000)
    @Column(name = "Num_of_ctn_for_this_bol")
    private String numofctnforthisbol;
    @Size(max = 2000000000)
    private String information;
    @Size(max = 2000000000)
    @Column(name = "PC_indicator")
    private String pCindicator;
    @Size(max = 2000000000)
    @Column(name = "Freight_value")
    private String freightvalue;
    @Size(max = 2000000000)
    @Column(name = "Freight_currency")
    private String freightcurrency;
    @Size(max = 2000000000)
    @Column(name = "Customs_value")
    private String customsvalue;
    @Size(max = 2000000000)
    @Column(name = "Customs_currency")
    private String customscurrency;
    @Size(max = 2000000000)
    @Column(name = "Insurance_value")
    private String insurancevalue;
    @Size(max = 2000000000)
    @Column(name = "Insurance_currency")
    private String insurancecurrency;
    @Size(max = 2000000000)
    @Column(name = "Transport_value")
    private String transportvalue;
    @Size(max = 2000000000)
    @Column(name = "Transport_currency")
    private String transportcurrency;
    @Size(max = 2000000000)
    @Column(name = "Location_code")
    private String locationcode;
    @Size(max = 2000000000)
    @Column(name = "Location_info")
    private String locationinfo;
    @JoinColumn(name = "id_general", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GeneralInfo idGeneral;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBol")
    private Collection<Container> containerCollection;

    public BillOfLanding() {
    }

    public BillOfLanding(Integer idBol) {
        this.idBol = idBol;
    }

    public BillOfLanding(Integer idBol, String bolreference, String linenumber, String bolnature) {
        this.idBol = idBol;
        this.bolreference = bolreference;
        this.linenumber = linenumber;
        this.bolnature = bolnature;
    }

    public Integer getIdBol() {
        return idBol;
    }

    public void setIdBol(Integer idBol) {
        this.idBol = idBol;
    }

    public String getBolreference() {
        return bolreference;
    }

    public void setBolreference(String bolreference) {
        this.bolreference = bolreference;
    }

    public String getLinenumber() {
        return linenumber;
    }

    public void setLinenumber(String linenumber) {
        this.linenumber = linenumber;
    }

    public String getBolnature() {
        return bolnature;
    }

    public void setBolnature(String bolnature) {
        this.bolnature = bolnature;
    }

    public String getBoltypecode() {
        return boltypecode;
    }

    public void setBoltypecode(String boltypecode) {
        this.boltypecode = boltypecode;
    }

    public String getMasterbolrefnumber() {
        return masterbolrefnumber;
    }

    public void setMasterbolrefnumber(String masterbolrefnumber) {
        this.masterbolrefnumber = masterbolrefnumber;
    }

    public String getUniquecarrierreference() {
        return uniquecarrierreference;
    }

    public void setUniquecarrierreference(String uniquecarrierreference) {
        this.uniquecarrierreference = uniquecarrierreference;
    }

    public String getPlaceofloadingcode() {
        return placeofloadingcode;
    }

    public void setPlaceofloadingcode(String placeofloadingcode) {
        this.placeofloadingcode = placeofloadingcode;
    }

    public String getPlaceofunloadingcode() {
        return placeofunloadingcode;
    }

    public void setPlaceofunloadingcode(String placeofunloadingcode) {
        this.placeofunloadingcode = placeofunloadingcode;
    }

    public String getExportercode() {
        return exportercode;
    }

    public void setExportercode(String exportercode) {
        this.exportercode = exportercode;
    }

    public String getExportername() {
        return exportername;
    }

    public void setExportername(String exportername) {
        this.exportername = exportername;
    }

    public String getExporteraddress() {
        return exporteraddress;
    }

    public void setExporteraddress(String exporteraddress) {
        this.exporteraddress = exporteraddress;
    }

    public String getNotifycode() {
        return notifycode;
    }

    public void setNotifycode(String notifycode) {
        this.notifycode = notifycode;
    }

    public String getNotifyname() {
        return notifyname;
    }

    public void setNotifyname(String notifyname) {
        this.notifyname = notifyname;
    }

    public String getNotifyaddress() {
        return notifyaddress;
    }

    public void setNotifyaddress(String notifyaddress) {
        this.notifyaddress = notifyaddress;
    }

    public String getConsigneecode() {
        return consigneecode;
    }

    public void setConsigneecode(String consigneecode) {
        this.consigneecode = consigneecode;
    }

    public String getConsigneename() {
        return consigneename;
    }

    public void setConsigneename(String consigneename) {
        this.consigneename = consigneename;
    }

    public String getConsigneeaddress() {
        return consigneeaddress;
    }

    public void setConsigneeaddress(String consigneeaddress) {
        this.consigneeaddress = consigneeaddress;
    }

    public String getNumberofpackages() {
        return numberofpackages;
    }

    public void setNumberofpackages(String numberofpackages) {
        this.numberofpackages = numberofpackages;
    }

    public String getPackagetypecode() {
        return packagetypecode;
    }

    public void setPackagetypecode(String packagetypecode) {
        this.packagetypecode = packagetypecode;
    }

    public String getGrossmass() {
        return grossmass;
    }

    public void setGrossmass(String grossmass) {
        this.grossmass = grossmass;
    }

    public String getShippingmarks() {
        return shippingmarks;
    }

    public void setShippingmarks(String shippingmarks) {
        this.shippingmarks = shippingmarks;
    }

    public String getGoodsdescription() {
        return goodsdescription;
    }

    public void setGoodsdescription(String goodsdescription) {
        this.goodsdescription = goodsdescription;
    }

    public String getVolumeincubicmeters() {
        return volumeincubicmeters;
    }

    public void setVolumeincubicmeters(String volumeincubicmeters) {
        this.volumeincubicmeters = volumeincubicmeters;
    }

    public String getNumofctnforthisbol() {
        return numofctnforthisbol;
    }

    public void setNumofctnforthisbol(String numofctnforthisbol) {
        this.numofctnforthisbol = numofctnforthisbol;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getPCindicator() {
        return pCindicator;
    }

    public void setPCindicator(String pCindicator) {
        this.pCindicator = pCindicator;
    }

    public String getFreightvalue() {
        return freightvalue;
    }

    public void setFreightvalue(String freightvalue) {
        this.freightvalue = freightvalue;
    }

    public String getFreightcurrency() {
        return freightcurrency;
    }

    public void setFreightcurrency(String freightcurrency) {
        this.freightcurrency = freightcurrency;
    }

    public String getCustomsvalue() {
        return customsvalue;
    }

    public void setCustomsvalue(String customsvalue) {
        this.customsvalue = customsvalue;
    }

    public String getCustomscurrency() {
        return customscurrency;
    }

    public void setCustomscurrency(String customscurrency) {
        this.customscurrency = customscurrency;
    }

    public String getInsurancevalue() {
        return insurancevalue;
    }

    public void setInsurancevalue(String insurancevalue) {
        this.insurancevalue = insurancevalue;
    }

    public String getInsurancecurrency() {
        return insurancecurrency;
    }

    public void setInsurancecurrency(String insurancecurrency) {
        this.insurancecurrency = insurancecurrency;
    }

    public String getTransportvalue() {
        return transportvalue;
    }

    public void setTransportvalue(String transportvalue) {
        this.transportvalue = transportvalue;
    }

    public String getTransportcurrency() {
        return transportcurrency;
    }

    public void setTransportcurrency(String transportcurrency) {
        this.transportcurrency = transportcurrency;
    }

    public String getLocationcode() {
        return locationcode;
    }

    public void setLocationcode(String locationcode) {
        this.locationcode = locationcode;
    }

    public String getLocationinfo() {
        return locationinfo;
    }

    public void setLocationinfo(String locationinfo) {
        this.locationinfo = locationinfo;
    }

    public GeneralInfo getIdGeneral() {
        return idGeneral;
    }

    public void setIdGeneral(GeneralInfo idGeneral) {
        this.idGeneral = idGeneral;
    }

    @XmlTransient
    public Collection<Container> getContainerCollection() {
        return containerCollection;
    }

    public void setContainerCollection(Collection<Container> containerCollection) {
        this.containerCollection = containerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBol != null ? idBol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BillOfLanding)) {
            return false;
        }
        BillOfLanding other = (BillOfLanding) object;
        if ((this.idBol == null && other.idBol != null) || (this.idBol != null && !this.idBol.equals(other.idBol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.BillOfLanding[ idBol=" + idBol + " ]";
    }
    
}
