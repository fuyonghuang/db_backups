package com.jt.db_backups.runBackUps;

import com.jt.db_backups.utils.ExportUtil;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import sun.util.resources.LocaleData;

/**
 * Created by  2018/10/18.
 */
@Service
public class loginLogBackUps {
  @Value("${sql.sqlStr1}")
  private String sqlStr1;
  @Autowired
  JdbcTemplate jdbcTemplate;
   public void  backUps(){

     String endTime = LocalDate.now().toString()+" 00:00:00";
     String startTime = LocalDateTime.now().withHour(2).format(DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm:ss")).toString();
     ArrayList<Object> arrayList = new ArrayList<>();
     arrayList.add(startTime);
     arrayList.add(endTime);

     List<Map<String, Object>> maps = jdbcTemplate.queryForList(sqlStr1, arrayList.toArray());
     String mapKey = "";
     for (Map<String,Object> map: maps
     ) {
       mapKey=map.keySet().toString();
       break;
     }
     mapKey   = mapKey.replaceAll("\\[", "");
     mapKey= mapKey.replaceAll("\\]","");
     ExportUtil.doExport(maps,mapKey,mapKey);

   }

}
