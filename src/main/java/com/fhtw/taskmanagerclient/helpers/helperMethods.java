package com.fhtw.taskmanagerclient.helpers;

import com.fhtw.taskmanagerclient.model.dto.AssociateDto;
import com.fhtw.taskmanagerclient.model.dto.GetAllAssociatesByManagerIdResponse;
import com.fhtw.taskmanagerclient.model.dto.TaskDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class helperMethods {

    public static ObservableList<associateTaskListItem> associateTaskListFactory(GetAllAssociatesByManagerIdResponse associates){

        ObservableList<associateTaskListItem> list = FXCollections.observableArrayList();

        for (AssociateDto associate:associates.getAssociates()) {
            for (TaskDto task:associate.getTasks()) {
                list.add(new associateTaskListItem(
                        associate.getAssociateName(),
                        task.getEmployeeTask(),
                        task.getEmployeeDateFrom(),
                        task.getEmployeeHoursSpent()));
            }
        }

        return list;

    }
}
