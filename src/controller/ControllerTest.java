package controller;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import java.net.InetAddress;
import static network.Message.DataType.Text;
import static org.junit.Assert.*;

//JUNIT 4
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControllerTest {

    static Controller controller = Controller.getController();
    String nameUser = "Leo";
    String dest = "Vincent";

    @org.junit.Test
    public void test1Connect() throws Exception {
        Thread.sleep(1000);
        controller.connect(nameUser);
        Thread.sleep(2000);
    }

    @org.junit.Test
    public void test2AddUser() throws Exception {
        controller.addUser(dest, InetAddress.getLocalHost());
        Thread.sleep(1000);
    }

    @org.junit.Test
    public void test3GetCurrentUserPseudo() throws Exception {
        assertEquals(nameUser,controller.getCurrentUserPseudo());
    }

    @org.junit.Test
    public void test4DeliverMessage() throws Exception {
        network.Message m = new network.Message(Text, "coucou", dest, nameUser);
        controller.deliverMessage(m);
        Thread.sleep(500);
        controller.userPan.get(nameUser).getMsgView().setVisible(true);
        Thread.sleep(2000);
    }

    @org.junit.Test
    public void test5Disconnect() throws Exception {
        controller.disconnect();
    }

}