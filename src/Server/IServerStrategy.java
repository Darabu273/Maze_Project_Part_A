package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * IServerStrategy is an interface, includes the applyStrategy method.
 */

public interface IServerStrategy {
    void ServerStrategy(InputStream inFromClient, OutputStream outToClient);

}

