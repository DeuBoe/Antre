package id.deuboe.antre;

public class Model {

    private String name, ktp, dateOfBirth, profession, idKtp, address, idPowerOfAttorney, date, choice;

    public Model() {

    }

    public Model(String name_model, String ktp_model, String dateOfBirth_model, String profession_model, String idKtp_model, String address_model, String idPowerOfAttorney_model, String date_model) {
        this.name = name_model;
        this.ktp = ktp_model;
        this.dateOfBirth = dateOfBirth_model;
        this.profession = profession_model;
        this.idKtp = idKtp_model;
        this.address = address_model;
        this.idPowerOfAttorney = idPowerOfAttorney_model;
        this.date = date_model;
    }

    public String getName() {
        return name;
    }

    public String getKtp() {
        return ktp;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getProfession() {
        return profession;
    }

    public String getIdKtp() {
        return idKtp;
    }

    public String getAddress() {
        return address;
    }

    public String getIdPowerOfAttorney() {
        return idPowerOfAttorney;
    }

    public String getDate() {
        return date;
    }

    public String getChoice() {
        return choice;
    }
}
