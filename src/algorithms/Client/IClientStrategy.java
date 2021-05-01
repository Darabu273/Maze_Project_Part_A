package algorithms.Client;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * IClientStrategy is an interface, includes the clientStrategy method that implemented in RunCommunicateWithServers class.
 */

public interface IClientStrategy {
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);

}
