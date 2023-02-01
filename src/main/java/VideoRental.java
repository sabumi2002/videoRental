import dbConn.ConnectionMaker;
import dbConn.MySqlConnectionMaker;
import viewer.LoginViewer;

public class VideoRental {
    public static void main(String[] args) {
        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        LoginViewer loginViewer = new LoginViewer(connectionMaker);
        loginViewer.showIndex();

    }
}
