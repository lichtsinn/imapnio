package com.lafaspot.imapnio.listener;

import java.util.List;

import com.lafaspot.imapnio.client.IMAPSession;
import com.sun.mail.imap.protocol.IMAPResponse;

/**
 * Listener interface to be implemented by the IMAP client. Will call the client back: (1) When the server sends a OK response to a tag (2) On any
 * event because of IDLE
 *
 * @author kraman
 *
 */
public interface SessionListener extends ClientListener {
	/**
	 * Will be called when the session/socket is connected.
	 * @param session IMAPSession
	 */
	void onConnect(final IMAPSession session);
    
    /**
     * Will be called when the session/socket is disconnected.
     * @param session IMAPSession
     */
    void onDisconnect(final IMAPSession session);
    
    
}
