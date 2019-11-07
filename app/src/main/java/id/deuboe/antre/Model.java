package id.deuboe.antre;

public class Model {

    private String name, ktp, dateOfBirth, profession, idKtp, address, idPowerOfAttorney, today, spinner, id, date;

    public Model() {

    }

    public Model(
        String name,
        String ktp,
        String dateOfBirth,
        String profession,
        String idKtp,
        String address,
        String idPowerOfAttorney,
        String today,
        String id,
        String spinner,
        String date) {
        this.name = name;
        this.ktp = ktp;
        this.dateOfBirth = dateOfBirth;
        this.profession = profession;
        this.idKtp = idKtp;
        this.address = address;
        this.idPowerOfAttorney = idPowerOfAttorney;
        this.today = today;
        this.id = id;
        this.spinner = spinner;
        this.date = date;
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

    public String getToday() {
        return today;
    }

    public String getId() {
        return id;
    }

    public String getSpinner() {
        return spinner;
    }

    public String getDate() {
        return date;
    }

}
