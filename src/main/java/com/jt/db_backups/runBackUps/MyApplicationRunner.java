package com.jt.db_backups.runBackUps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by  2018/10/18.
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {

  @Autowired
private loginLogBackUps logBackUps;
  @Override
  public void run(ApplicationArguments var1) throws Exception{
    System.out.println("MyApplicationRunner1!");
    logBackUps.backUps();
  }
}
