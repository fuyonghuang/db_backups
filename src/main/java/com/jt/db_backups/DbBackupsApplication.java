package com.jt.db_backups;

import com.jt.db_backups.runBackUps.loginLogBackUps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbBackupsApplication {


  public static void main(String[] args) {
    SpringApplication.run(DbBackupsApplication.class, args);
  }
}
