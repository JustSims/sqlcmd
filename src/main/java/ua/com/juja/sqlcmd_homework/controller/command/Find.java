package ua.com.juja.sqlcmd_homework.controller.command;

import ua.com.juja.sqlcmd_homework.model.DataSet;
import ua.com.juja.sqlcmd_homework.model.DatabaseManager;
import ua.com.juja.sqlcmd_homework.view.View;

import java.util.List;
import java.util.Set;

/**
 * Created by Sims on 12/10/2015.
 */
public class Find implements Command {
    private final DatabaseManager manager;
    private final View view;

    public Find(DatabaseManager manager, View view) {

        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("find|");
    }

    @Override
    public void process(String command) {
            String[] data = command.split("\\|");
            String tableName = data[1]; //TODO to add validation
            Set<String> tableColumns = manager.getTableColumns(tableName);
            printHeader(tableColumns);
            List<DataSet> tableData = manager.getTableData(tableName);
            printTable(tableData);
        }

    private void printTable(List<DataSet> tableData) {
        for (DataSet row: tableData){
            printRow(row);
        }
        view.write("-----------------");
    }

    private void printRow(DataSet row) {
        Object[] values = row.getValues();
        String result = "";
        for (Object value: values){
            result += value + "|";
        }
        view.write(result);
    }

    private void printHeader(Set<String> tableColumns) {
        String result = "";
        for (String name: tableColumns){
            result += name + "|";
        }
        view.write("-----------------");
        view.write(result);
        view.write("-----------------");
    }
    }


