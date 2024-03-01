package com.example.myapplication;

public class Client {
    private int idClient;
    private String nom;
    private String email;
    private String motPasse;

    public Client(String nom, String email, String motPasse)
    {
        this.nom = nom;
        this.email = email;
        this.motPasse = motPasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    @Override
    public String toString() {
        return "com.example.myapplication.Client{" +
                "idClient=" + idClient +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", motPasse='" + motPasse + '\'' +
                '}';
    }
}
