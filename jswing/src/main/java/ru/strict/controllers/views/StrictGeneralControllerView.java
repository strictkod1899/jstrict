package ru.strict.controllers.views;

import ru.strict.utils.StrictUtilLogger;
import ru.strict.controllers.views.dialogs.StrictControllerDialogDefault;
import ru.strict.controllers.views.frames.StrictControllerFrameDefault;
import ru.strict.controllers.views.panels.StrictControllerPanelContent;
import ru.strict.controllers.views.panels.StrictControllerPanelState;
import ru.strict.models.panels.StrictModelPanelContent;
import ru.strict.models.panels.StrictModelPanelState;
import ru.strict.views.components.StrictPanelContent;
import ru.strict.views.components.StrictPanelState;

import java.awt.*;

/**
 * Класс предоставляет методы используемые в контроллерах окон фрейма, диалога и панели
 */
public class StrictGeneralControllerView {

    private StrictControllerFormBase controller;

    public StrictGeneralControllerView(StrictControllerFormBase controller) {
        this.controller = controller;
    }

    /**
     * Инициализация контроллера панели состояния
     */
    public void initControllerPanelState(){
        StrictUtilLogger.info(StrictGeneralControllerView.class, "initControllerPanelState - started");
        StrictModelPanelState modelPanelState = new StrictModelPanelState();
        modelPanelState.setTitle(controller.getModel().getTitle());

        StrictControllerPanelState controllerPanelState = new StrictControllerPanelState(new StrictPanelState(),
                modelPanelState);
        controllerPanelState.getModel().setParent((Window)controller.getObject());
        controllerPanelState.getModel().setActionExit(controller::destroy);
        controllerPanelState.getModel().setPathIcon(controller.getModel().getPathIcon());

        if(controller instanceof StrictControllerDialogDefault) {
            controllerPanelState.getModel().setVisibleTurn(((StrictControllerDialogDefault)controller).getModel().isVisibleTurn());
            controllerPanelState.getModel().setVisibleChangeSize(((StrictControllerDialogDefault)controller).getModel().isVisibleChangeSize());
            controllerPanelState.getModel().setVisibleExit(((StrictControllerDialogDefault)controller).getModel().isVisibleExit());

            ((StrictControllerDialogDefault)controller).getObject().setPanelState(controllerPanelState.build());
        } else if(controller instanceof StrictControllerFrameDefault) {
            controllerPanelState.getModel().setVisibleTurn(((StrictControllerFrameDefault)controller).getModel().isVisibleTurn());
            controllerPanelState.getModel().setVisibleChangeSize(((StrictControllerFrameDefault)controller).getModel().isVisibleChangeSize());
            controllerPanelState.getModel().setVisibleExit(((StrictControllerFrameDefault)controller).getModel().isVisibleExit());

            ((StrictControllerFrameDefault)controller).getObject().setPanelState(controllerPanelState.build());
        }
        StrictUtilLogger.info(StrictGeneralControllerView.class, "initControllerPanelState - finished");
    }

    /**
     * Инициализация контроллера панели содержимого
     */
    public void initControllerPanelContent(){
        StrictUtilLogger.info(StrictGeneralControllerView.class, "initControllerPanelContent - started");
        StrictControllerPanelContent controllerPanelContent = new StrictControllerPanelContent(new StrictPanelContent()
                , new StrictModelPanelContent());
        if(controller instanceof StrictControllerDialogDefault)
            ((StrictControllerDialogDefault)controller).getObject().setPanelContent(controllerPanelContent.build());
        else if(controller instanceof StrictControllerFrameDefault)
            ((StrictControllerFrameDefault)controller).getObject().setPanelContent(controllerPanelContent.build());
        StrictUtilLogger.info(StrictGeneralControllerView.class, "initControllerPanelContent - finished");
    }


}
