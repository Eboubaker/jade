package main;

import communication.BlackBoardController;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

import java.util.List;

public class Agency {
    private final String name;

    // for testing one agency
    public static void main(String[] args) {
        var agency = new Agency("EL-OUED");
        agency.start();
    }

    public String getName() {
        return name;
    }

    public Agency(String name) {
        this.name = name;
    }

    public void start() {
        Runtime rt = Runtime.instance();

        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.GUI, "true"); // Enable the GUI
        profile.setParameter(Profile.CONTAINER_NAME, "agency-" + name); // Set the platform name
//        profile.setParameter(Profile.MAIN_HOST, "localhost"); // Set the host
//        profile.setParameter(Profile.MAIN_PORT, "1099"); // Set the port

        AgentContainer mainContainer = rt.createMainContainer(profile);

        try {
            List<Class<?>> agentClasses = List.of(
                    BlackBoardController.class,
                    AGCInterfaceAgent.class,
                    AllReceiverAgent.class,
                    TPEInterfaceAgent.class,
                    ATMInterfaceAgent.class
            );
            for (Class<?> agentClass : agentClasses) {
                AgentController agentController = mainContainer.createNewAgent(
                        agentClass.getSimpleName(), // Assign a nickname to the agent
                        agentClass.getCanonicalName(),
                        null
                );
                agentController.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
