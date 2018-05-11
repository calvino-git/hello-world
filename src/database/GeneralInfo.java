/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author itsystem
 */
@Entity
@Table(name = "GENERAL_INFO", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneralInfo.findAll", query = "SELECT g FROM GeneralInfo g"),
    @NamedQuery(name = "GeneralInfo.findById", query = "SELECT g FROM GeneralInfo g WHERE g.id = :id"),
    @NamedQuery(name = "GeneralInfo.findByCustomsOfficeCode", query = "SELECT g FROM GeneralInfo g WHERE g.customsOfficeCode = :customsOfficeCode"),
    @NamedQuery(name = "GeneralInfo.findByVoyageNumber", query = "SELECT g FROM GeneralInfo g WHERE g.voyageNumber = :voyageNumber"),
    @NamedQuery(name = "GeneralInfo.findByDateDeparture", query = "SELECT g FROM GeneralInfo g WHERE g.dateDeparture = :dateDeparture"),
    @NamedQuery(name = "GeneralInfo.findByDateArrival", query = "SELECT g FROM GeneralInfo g WHERE g.dateArrival = :dateArrival"),
    @NamedQuery(name = "GeneralInfo.findByTimeArrival", query = "SELECT g FROM GeneralInfo g WHERE g.timeArrival = :timeArrival"),
    @NamedQuery(name = "GeneralInfo.findByDateLastDischarge", query = "SELECT g FROM GeneralInfo g WHERE g.dateLastDischarge = :dateLastDischarge"),
    @NamedQuery(name = "GeneralInfo.findByTotalNumberOfBols", query = "SELECT g FROM GeneralInfo g WHERE g.totalNumberOfBols = :totalNumberOfBols"),
    @NamedQuery(name = "GeneralInfo.findByTotalnumberofpackages", query = "SELECT g FROM GeneralInfo g WHERE g.totalnumberofpackages = :totalnumberofpackages"),
    @NamedQuery(name = "GeneralInfo.findByTotalnumberofcontainers", query = "SELECT g FROM GeneralInfo g WHERE g.totalnumberofcontainers = :totalnumberofcontainers"),
    @NamedQuery(name = "GeneralInfo.findByTotalgrossmass", query = "SELECT g FROM GeneralInfo g WHERE g.totalgrossmass = :totalgrossmass"),
    @NamedQuery(name = "GeneralInfo.findByModeoftransportcode", query = "SELECT g FROM GeneralInfo g WHERE g.modeoftransportcode = :modeoftransportcode"),
    @NamedQuery(name = "GeneralInfo.findByIdentityoftransporter", query = "SELECT g FROM GeneralInfo g WHERE g.identityoftransporter = :identityoftransporter"),
    @NamedQuery(name = "GeneralInfo.findByNationalityoftransportercode", query = "SELECT g FROM GeneralInfo g WHERE g.nationalityoftransportercode = :nationalityoftransportercode"),
    @NamedQuery(name = "GeneralInfo.findByPlaceoftransporter", query = "SELECT g FROM GeneralInfo g WHERE g.placeoftransporter = :placeoftransporter"),
    @NamedQuery(name = "GeneralInfo.findByRegistrationnumberoftransportcode", query = "SELECT g FROM GeneralInfo g WHERE g.registrationnumberoftransportcode = :registrationnumberoftransportcode"),
    @NamedQuery(name = "GeneralInfo.findByDateofregistration", query = "SELECT g FROM GeneralInfo g WHERE g.dateofregistration = :dateofregistration"),
    @NamedQuery(name = "GeneralInfo.findByMasterinformation", query = "SELECT g FROM GeneralInfo g WHERE g.masterinformation = :masterinformation"),
    @NamedQuery(name = "GeneralInfo.findByCarriercode", query = "SELECT g FROM GeneralInfo g WHERE g.carriercode = :carriercode"),
    @NamedQuery(name = "GeneralInfo.findByCarriername", query = "SELECT g FROM GeneralInfo g WHERE g.carriername = :carriername"),
    @NamedQuery(name = "GeneralInfo.findByCarrieraddress", query = "SELECT g FROM GeneralInfo g WHERE g.carrieraddress = :carrieraddress"),
    @NamedQuery(name = "GeneralInfo.findByShippingAgentcode", query = "SELECT g FROM GeneralInfo g WHERE g.shippingAgentcode = :shippingAgentcode"),
    @NamedQuery(name = "GeneralInfo.findByShippingAgentname", query = "SELECT g FROM GeneralInfo g WHERE g.shippingAgentname = :shippingAgentname"),
    @NamedQuery(name = "GeneralInfo.findByPlaceofdeparturecode", query = "SELECT g FROM GeneralInfo g WHERE g.placeofdeparturecode = :placeofdeparturecode"),
    @NamedQuery(name = "GeneralInfo.findByPlaceofdestinationcode", query = "SELECT g FROM GeneralInfo g WHERE g.placeofdestinationcode = :placeofdestinationcode"),
    @NamedQuery(name = "GeneralInfo.findByTonnagenetweight", query = "SELECT g FROM GeneralInfo g WHERE g.tonnagenetweight = :tonnagenetweight"),
    @NamedQuery(name = "GeneralInfo.findByTonnagegrossweight", query = "SELECT g FROM GeneralInfo g WHERE g.tonnagegrossweight = :tonnagegrossweight")})
public class GeneralInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    @Size(max = 2000000000)
    @Column(name = "customs_office_code")
    private String customsOfficeCode;
    @Size(max = 2000000000)
    @Column(name = "voyage_number")
    private String voyageNumber;
    @Size(max = 2000000000)
    @Column(name = "date_departure")
    private String dateDeparture;
    @Size(max = 2000000000)
    @Column(name = "date_arrival")
    private String dateArrival;
    @Size(max = 2000000000)
    @Column(name = "time_arrival")
    private String timeArrival;
    @Size(max = 2000000000)
    @Column(name = "date_last_discharge")
    private String dateLastDischarge;
    @Size(max = 2000000000)
    @Column(name = "total_number_of_bols")
    private String totalNumberOfBols;
    @Size(max = 2000000000)
    @Column(name = "Total_number_of_packages")
    private String totalnumberofpackages;
    @Size(max = 2000000000)
    @Column(name = "Total_number_of_containers")
    private String totalnumberofcontainers;
    @Size(max = 2000000000)
    @Column(name = "Total_gross_mass")
    private String totalgrossmass;
    @Size(max = 2000000000)
    @Column(name = "Mode_of_transport_code")
    private String modeoftransportcode;
    @Size(max = 2000000000)
    @Column(name = "Identity_of_transporter")
    private String identityoftransporter;
    @Size(max = 2000000000)
    @Column(name = "Nationality_of_transporter_code")
    private String nationalityoftransportercode;
    @Size(max = 2000000000)
    @Column(name = "Place_of_transporter")
    private String placeoftransporter;
    @Size(max = 2000000000)
    @Column(name = "Registration_number_of_transport_code")
    private String registrationnumberoftransportcode;
    @Size(max = 2000000000)
    @Column(name = "Date_of_registration")
    private String dateofregistration;
    @Size(max = 2000000000)
    @Column(name = "Master_information")
    private String masterinformation;
    @Size(max = 2000000000)
    @Column(name = "Carrier_code")
    private String carriercode;
    @Size(max = 2000000000)
    @Column(name = "Carrier_name")
    private String carriername;
    @Size(max = 2000000000)
    @Column(name = "Carrier_address")
    private String carrieraddress;
    @Size(max = 2000000000)
    @Column(name = "Shipping_Agent_code")
    private String shippingAgentcode;
    @Size(max = 2000000000)
    @Column(name = "Shipping_Agent_name")
    private String shippingAgentname;
    @Size(max = 2000000000)
    @Column(name = "Place_of_departure_code")
    private String placeofdeparturecode;
    @Size(max = 2000000000)
    @Column(name = "Place_of_destination_code")
    private String placeofdestinationcode;
    @Size(max = 2000000000)
    @Column(name = "Tonnage_net_weight")
    private String tonnagenetweight;
    @Size(max = 2000000000)
    @Column(name = "Tonnage_gross_weight")
    private String tonnagegrossweight;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGeneral")
    private Collection<BillOfLanding> billOfLandingCollection;

    public GeneralInfo() {
    }

    public GeneralInfo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomsOfficeCode() {
        return customsOfficeCode;
    }

    public void setCustomsOfficeCode(String customsOfficeCode) {
        this.customsOfficeCode = customsOfficeCode;
    }

    public String getVoyageNumber() {
        return voyageNumber;
    }

    public void setVoyageNumber(String voyageNumber) {
        this.voyageNumber = voyageNumber;
    }

    public String getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(String dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public String getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(String dateArrival) {
        this.dateArrival = dateArrival;
    }

    public String getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(String timeArrival) {
        this.timeArrival = timeArrival;
    }

    public String getDateLastDischarge() {
        return dateLastDischarge;
    }

    public void setDateLastDischarge(String dateLastDischarge) {
        this.dateLastDischarge = dateLastDischarge;
    }

    public String getTotalNumberOfBols() {
        return totalNumberOfBols;
    }

    public void setTotalNumberOfBols(String totalNumberOfBols) {
        this.totalNumberOfBols = totalNumberOfBols;
    }

    public String getTotalnumberofpackages() {
        return totalnumberofpackages;
    }

    public void setTotalnumberofpackages(String totalnumberofpackages) {
        this.totalnumberofpackages = totalnumberofpackages;
    }

    public String getTotalnumberofcontainers() {
        return totalnumberofcontainers;
    }

    public void setTotalnumberofcontainers(String totalnumberofcontainers) {
        this.totalnumberofcontainers = totalnumberofcontainers;
    }

    public String getTotalgrossmass() {
        return totalgrossmass;
    }

    public void setTotalgrossmass(String totalgrossmass) {
        this.totalgrossmass = totalgrossmass;
    }

    public String getModeoftransportcode() {
        return modeoftransportcode;
    }

    public void setModeoftransportcode(String modeoftransportcode) {
        this.modeoftransportcode = modeoftransportcode;
    }

    public String getIdentityoftransporter() {
        return identityoftransporter;
    }

    public void setIdentityoftransporter(String identityoftransporter) {
        this.identityoftransporter = identityoftransporter;
    }

    public String getNationalityoftransportercode() {
        return nationalityoftransportercode;
    }

    public void setNationalityoftransportercode(String nationalityoftransportercode) {
        this.nationalityoftransportercode = nationalityoftransportercode;
    }

    public String getPlaceoftransporter() {
        return placeoftransporter;
    }

    public void setPlaceoftransporter(String placeoftransporter) {
        this.placeoftransporter = placeoftransporter;
    }

    public String getRegistrationnumberoftransportcode() {
        return registrationnumberoftransportcode;
    }

    public void setRegistrationnumberoftransportcode(String registrationnumberoftransportcode) {
        this.registrationnumberoftransportcode = registrationnumberoftransportcode;
    }

    public String getDateofregistration() {
        return dateofregistration;
    }

    public void setDateofregistration(String dateofregistration) {
        this.dateofregistration = dateofregistration;
    }

    public String getMasterinformation() {
        return masterinformation;
    }

    public void setMasterinformation(String masterinformation) {
        this.masterinformation = masterinformation;
    }

    public String getCarriercode() {
        return carriercode;
    }

    public void setCarriercode(String carriercode) {
        this.carriercode = carriercode;
    }

    public String getCarriername() {
        return carriername;
    }

    public void setCarriername(String carriername) {
        this.carriername = carriername;
    }

    public String getCarrieraddress() {
        return carrieraddress;
    }

    public void setCarrieraddress(String carrieraddress) {
        this.carrieraddress = carrieraddress;
    }

    public String getShippingAgentcode() {
        return shippingAgentcode;
    }

    public void setShippingAgentcode(String shippingAgentcode) {
        this.shippingAgentcode = shippingAgentcode;
    }

    public String getShippingAgentname() {
        return shippingAgentname;
    }

    public void setShippingAgentname(String shippingAgentname) {
        this.shippingAgentname = shippingAgentname;
    }

    public String getPlaceofdeparturecode() {
        return placeofdeparturecode;
    }

    public void setPlaceofdeparturecode(String placeofdeparturecode) {
        this.placeofdeparturecode = placeofdeparturecode;
    }

    public String getPlaceofdestinationcode() {
        return placeofdestinationcode;
    }

    public void setPlaceofdestinationcode(String placeofdestinationcode) {
        this.placeofdestinationcode = placeofdestinationcode;
    }

    public String getTonnagenetweight() {
        return tonnagenetweight;
    }

    public void setTonnagenetweight(String tonnagenetweight) {
        this.tonnagenetweight = tonnagenetweight;
    }

    public String getTonnagegrossweight() {
        return tonnagegrossweight;
    }

    public void setTonnagegrossweight(String tonnagegrossweight) {
        this.tonnagegrossweight = tonnagegrossweight;
    }

    @XmlTransient
    public Collection<BillOfLanding> getBillOfLandingCollection() {
        return billOfLandingCollection;
    }

    public void setBillOfLandingCollection(Collection<BillOfLanding> billOfLandingCollection) {
        this.billOfLandingCollection = billOfLandingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeneralInfo)) {
            return false;
        }
        GeneralInfo other = (GeneralInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.GeneralInfo[ id=" + id + " ]";
    }
    
}
