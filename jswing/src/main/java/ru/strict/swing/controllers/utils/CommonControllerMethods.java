package ru.strict.swing.controllers.utils;

import ru.strict.swing.controllers.panels.ControllerPanelState;
import ru.strict.swing.models.ModelFormDefault;
import ru.strict.swing.views.components.PanelContent;
import ru.strict.utils.UtilLogger;
import ru.strict.swing.controllers.panels.ControllerPanelContent;
import ru.strict.swing.models.panels.ModelPanelContent;
import ru.strict.swing.models.panels.ModelPanelState;
import ru.strict.swing.views.components.PanelState;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Класс предоставляет методы используемые в контроллерах окон фрейма, диалога и панели
 */
public class CommonControllerMethods {

    /**
     * Создать панель состояния
     */
    public static PanelState createPanelState(ModelFormDefault parentModel, Window parentWindow,
                                        ActionListener actionExit){
        UtilLogger.info(CommonControllerMethods.class, "createPanelState - started");
        ModelPanelState modelPanelState = new ModelPanelState();
        modelPanelState.setTitle(parentModel.getTitle());
        modelPanelState.setVisibleTurn(parentModel.isVisibleTurn());
        modelPanelState.setVisibleChangeSize(parentModel.isVisibleChangeSize());
        modelPanelState.setVisibleExit(parentModel.isVisibleExit());
        modelPanelState.setMoveForm(parentModel.isMoveForm());
        modelPanelState.setParent(parentWindow);
        modelPanelState.setActionExit(actionExit);
        modelPanelState.setPathIcon(parentModel.getPathIcon());

        ControllerPanelState controllerPanelState = new ControllerPanelState(
                new PanelState(),
                modelPanelState
        );

        UtilLogger.info(CommonControllerMethods.class, "createPanelState - finished");
        return controllerPanelState.build();
    }

    /**
     * Создать панель содержимого
     */
    public static PanelContent createPanelContent(){
        UtilLogger.info(CommonControllerMethods.class, "createPanelContent - started");
        ControllerPanelContent controllerPanelContent = new ControllerPanelContent(
                new PanelContent(),
                new ModelPanelContent()
        );

        UtilLogger.info(CommonControllerMethods.class, "createPanelContent - finished");
        return controllerPanelContent.build();
    }


}
