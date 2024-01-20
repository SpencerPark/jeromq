package zmq.io.net.ipc;

import java.net.InetSocketAddress;

import zmq.Options;
import zmq.SocketBase;
import zmq.io.IOThread;
import zmq.io.net.Address;
import zmq.io.net.tcp.TcpListener;

// fake Unix domain socket
public class IpcListener extends TcpListener
{
    public IpcListener(IOThread ioThread, SocketBase socket, final Options options)
    {
        super(ioThread, socket, options);

    }

    // Get the bound address for use with wildcards
    @Override
    public String getAddress()
    {
        Address.IZAddress address = super.getZAddress();
        if (((InetSocketAddress) address.address()).getPort() == 0) {
            return address(address);
        }
        return address.toString();
    }

    //  Set address to listen on.
    @Override
    public boolean setAddress(String addr)
    {
        IpcAddress address = new IpcAddress(addr);

        InetSocketAddress sock = (InetSocketAddress) address.address();
        return super.setAddress(sock);
    }
}