package com.pawsshop.model; import javax.persistence.*;
@Entity public class DataMigration { @Id private String migrationKey; public DataMigration(){} public DataMigration(String key){migrationKey=key;} public String getMigrationKey(){return migrationKey;} }
