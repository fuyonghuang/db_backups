package com.jt.db_backups.runBackUps;

import com.jt.db_backups.utils.ExportUtil;
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

/**
 * Created by  2018/10/18.
 */
@Service
public class LogBackUps {

  @Value("${sql.userLoginLogSql}")
  private String userLoginLogSql;
  @Value("${sql.userActionLogSql}")
  private String userActionLogSql;
  @Value("${file.path}")
  private String path;
  @Autowired
  JdbcTemplate jdbcTemplate;

  /**
   * 备份用户登录日志
   */
  public void loginLogBackUps() {

    String endTime =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH")) + ":00:00";
    String startTime =
        LocalDateTime.now().minusHours(12).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH"))
            + ":00:00";
    ArrayList<Object> arrayList = new ArrayList<>();
    arrayList.add(startTime);
    arrayList.add(endTime);
    List<Map<String, Object>> maps = jdbcTemplate.queryForList(userLoginLogSql, arrayList.toArray());
    String mapKey = "";
    for (Map<String, Object> map : maps
    ) {
      mapKey = map.keySet().toString();
      break;
    }
    mapKey = mapKey.replaceAll("\\[", "");
    mapKey = mapKey.replaceAll("\\]", "");
    boolean b = ExportUtil.doExport(maps, mapKey, mapKey,
        path + "/loginLog" + startTime.replaceAll(" ", "-").replaceAll(":", "-") + "-" + endTime.replaceAll(" ", "-").replaceAll(":", "-")
            + ".csv",
        path + "/loginLog" + startTime.replaceAll(" ", "-").replaceAll(":", "-") + "-" + endTime.replaceAll(" ", "-").replaceAll(":", "-")
            + ".csv");

  }

  /**
   * 备份用户操作日志
   */
  public void userActionLogBackUps() {
    String endTime =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH")) + ":00:00";
    String startTime =
        LocalDateTime.now().minusHours(12).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH"))
            + ":00:00";
    ArrayList<Object> arrayList = new ArrayList<>();
    arrayList.add(startTime);
    arrayList.add(endTime);
    List<Map<String, Object>> maps = jdbcTemplate.queryForList(userActionLogSql, arrayList.toArray());
    String mapKey = "";
    for (Map<String, Object> map : maps
    ) {
      mapKey = map.keySet().toString();
      break;
    }
    mapKey = mapKey.replaceAll("\\[", "");
    mapKey = mapKey.replaceAll("\\]", "");
    ExportUtil.doExport(maps, mapKey, mapKey,
        path + "/userAction" + startTime.replaceAll(" ", "-").replaceAll(":", "-") + "-" + endTime.replaceAll(" ", "-")
            .replaceAll(":", "-") + ".csv",
        path + "/userAction" + startTime.replaceAll(" ", "-").replaceAll(":", "-") + "-" + endTime.replaceAll(" ", "-").replaceAll(":", "-")
            + ".csv");

  }

  public static void main(String[] args) {
    int startTime = LocalTime.now().getHour();
    String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH"));
    System.out.println(format + ">>>>>" + startTime);
  }

}
