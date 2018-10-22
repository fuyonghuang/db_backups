package com.jt.db_backups.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by  2018/10/18.
 */
public class ExportUtil {

  /**
   * CSV文件列分隔符
   */
  private static final String CSV_COLUMN_SEPARATOR = ",";

  /**
   * CSV文件列分隔符
   */
  private static final String CSV_RN = "\r\n";

  /**
   *
   * @param dataList
   * @param colNames
   * @param mapKey
   * @param templetFilePath
   * @param exportFilePath
   * @return
   */
  public static boolean doExport(List<Map<String, Object>> dataList, String colNames, String mapKey,
      String templetFilePath, String exportFilePath) {
    try {

      File exportFile = new File(templetFilePath);
      File templetFile = new File(exportFilePath);

      if (!exportFile.exists()) {
        exportFile.createNewFile();
      }

      FileOutputStream out = new FileOutputStream(exportFile);
      FileInputStream fis = new FileInputStream(templetFile);
      StringBuffer buf = new StringBuffer();

      String[] colNamesArr = null;
      String[] mapKeyArr = null;

      colNamesArr = colNames.split(",");
      mapKeyArr = mapKey.split(",");

      // 完成数据csv文件的封装
      // 输出列头
      for (String aColNamesArr : colNamesArr) {
        buf.append(aColNamesArr).append(CSV_COLUMN_SEPARATOR);
      }
      buf.append(CSV_RN);
      // 输出数据
      if (null != dataList) {
        for (Map<String, Object> aDataList : dataList) {
          for (String aMapKeyArr : mapKeyArr) {
            buf.append(aDataList.get(aMapKeyArr.trim())).append(CSV_COLUMN_SEPARATOR);
          }
          buf.append(CSV_RN);
        }
      }
      // 写出响应
      out.write(buf.toString().getBytes("GBK"));
      out.flush();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }


}
