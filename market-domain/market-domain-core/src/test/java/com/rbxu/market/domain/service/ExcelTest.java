package com.rbxu.market.domain.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.AbstractCellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class ExcelTest {

    @Test
    public void test() {
        // 文件路径
        String filePath = "C:\\Users\\ASUS\\Desktop\\excel\\file.xlsx";


        // 写入数据
        EasyExcel.write(filePath, DemoData.class)
                .registerWriteHandler(new DateColumnWidthStyleStrategy()) // 注册日期格式化处理器
                .sheet("Sheet1")
                .doWrite(getData());
    }

    private List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        list.add(new DemoData(new Date()));
        return list;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class DemoData {

        private Date date;


    }

    public class DateColumnWidthStyleStrategy extends AbstractCellWriteHandler {

        @Override
        public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
            log.info("afterCellDispose");
            super.afterCellDispose(writeSheetHolder, writeTableHolder, cellDataList, cell, head, relativeRowIndex, isHead);
        }

        @Override
        public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, CellData cellData, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {

            log.info("afterCellDataConverted");
            DataFormat dataFormat = writeSheetHolder.getParentWriteWorkbookHolder().getCachedWorkbook().createDataFormat();
            cell.getCellStyle().setDataFormat(dataFormat.getFormat("yyyy-mm-dd"));
            super.afterCellDataConverted(writeSheetHolder, writeTableHolder, cellData, cell, head, relativeRowIndex, isHead);
        }

        @Override
        public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
            log.info("beforeCellCreate");

            super.beforeCellCreate(writeSheetHolder, writeTableHolder, row, head, columnIndex, relativeRowIndex, isHead);
        }

        @Override
        public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
            log.info("afterCellCreate");

            DataFormat dataFormat = writeSheetHolder.getParentWriteWorkbookHolder().getCachedWorkbook().createDataFormat();
            cell.getCellStyle().setDataFormat(dataFormat.getFormat("yyyy-mm-dd"));
            super.afterCellCreate(writeSheetHolder, writeTableHolder, cell, head, relativeRowIndex, isHead);
        }

    }
}