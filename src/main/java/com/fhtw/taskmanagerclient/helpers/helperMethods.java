package com.fhtw.taskmanagerclient.helpers;

import com.fhtw.taskmanagerclient.model.dto.AssociateDto;
import com.fhtw.taskmanagerclient.model.dto.GetAllAssociatesByManagerIdResponse;
import com.fhtw.taskmanagerclient.model.dto.TaskDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**

 A helper class that provides methods to facilitate different tasks.
 */
public class helperMethods {
    /**

     Creates an observable list of associateTaskListItem objects from a GetAllAssociatesByManagerIdResponse object.

     @param associates The GetAllAssociatesByManagerIdResponse object from which to create the observable list.

     @return An observable list of associateTaskListItem objects created from the associates in the GetAllAssociatesByManagerIdResponse object.
     */
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
