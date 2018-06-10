package ru.strict.swing.controllers.views;

import ru.strict.swing.controllers.views.frames.ControllerFrameDefault;
import ru.strict.swing.controllers.views.panels.ControllerPanelState;
import ru.strict.swing.views.components.PanelContent;
import ru.strict.utils.UtilLogger;
import ru.strict.swing.controllers.views.dialogs.ControllerDialogDefault;
import ru.strict.swing.controllers.views.panels.ControllerPanelContent;
import ru.strict.swing.models.panels.ModelPanelContent;
import ru.strict.swing.models.panels.ModelPanelState;
import ru.strict.swing.views.components.PanelState;

import java.awt.*;

/**
 * Класс предоставляет методы используемые в контроллерах окон фрейма, диалога и панели
 */
public class GeneralControllerView {

    private ControllerFormBase controller;

    public GeneralControllerView(ControllerFormBase controller) {
        this.controller = controller;
    }

    /**
     * Инициализация контроллера панели состояния
     */
    public void initControllerPanelState(){
        UtilLogger.info(GeneralControllerView.class, "initControllerPanelState - started");
        ModelPanelState modelPanelState = new ModelPanelState();
        modelPanelState.setTitle(controller.getModel().getTitle());

        ControllerPanelState controllerPanelState = new ControllerPanelState(new PanelState(),
                modelPanelState);
        controllerPanelState.getModel().setParent((Window)controller.getObject());
        controllerPanelState.getModel().setActionExit(controller::destroy);
        controllerPanelState.getModel().setPathIcon(controller.getModel().getPathIcon());

        if(controller instanceof ControllerDialogDefault) {
            controllerPanelState.getModel().setVisibleTurn(((ControllerDialogDefault)controller).getModel().isVisibleTurn());
            controllerPanelState.getModel().setVisibleChangeSize(((ControllerDialogDefault)controller).getModel().isVisibleChangeSize());
            controllerPanelState.getModel().setVisibleExit(((ControllerDialogDefault)controller).getModel().isVisibleExit());

            ((ControllerDialogDefault)controller).getObject().setPanelState(controllerPanelState.build());
        } else if(controller instanceof ControllerFrameDefault) {
            controllerPanelState.getModel().setVisibleTurn(((ControllerFrameDefault)controller).getModel().isVisibleTurn());
            controllerPanelState.getModel().setVisibleChangeSize(((ControllerFrameDefault)controller).getModel().isVisibleChangeSize());
            controllerPanelState.getModel().setVisibleExit(((ControllerFrameDefault)controller).getModel().isVisibleExit());

            ((ControllerFrameDefault)controller).getObject().setPanelState(controllerPanelState.build());
        }
        UtilLogger.info(GeneralControllerView.class, "initControllerPanelState - finished");
    }

    /**
     * Инициализация контроллера панели содержимого
     */
    public void initControllerPanelContent(){
        UtilLogger.info(GeneralControllerView.class, "initControllerPanelContent - started");
        ControllerPanelContent controllerPanelContent = new ControllerPanelContent(new PanelContent()
                , new ModelPanelContent());
        if(controller instanceof ControllerDialogDefault)
            ((ControllerDialogDefault)controller).getObject().setPanelContent(controllerPanelContent.build());
        else if(controller instanceof ControllerFrameDefault)
            ((ControllerFrameDefault)controller).getObject().setPanelContent(controllerPanelContent.build());
        UtilLogger.info(GeneralControllerView.class, "initControllerPanelContent - finished");
    }


}
