/**
 *
 */
package com.kl.mail.imapnioclient.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLException;

import org.slf4j.LoggerFactory;

import com.kl.mail.imapnioclient.config.IMAPClientConfig;
import com.kl.mail.imapnioclient.exception.IMAPSessionException;

/**
 * Netty based NIO IMAP client.
 *
 * @author kraman
 *
 */
public enum IMAPClient {
	
	INSTANCE;

    /** Client configuration. */
    private final IMAPClientConfig config;

    private Bootstrap bootstrap;

    /** Event loop group that will serve all channels for IMAP client. */
    private final EventLoopGroup group;


    /** logger. */
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(IMAPClient.class);

    /**
     * Constructs a NIO based IMAP client.
     */
    IMAPClient() {
        this.config = new IMAPClientConfig();
        this.bootstrap = new Bootstrap();
        this.group = new NioEventLoopGroup(config.getNumThreads());
    }

    /**
     * Create a new IMAP session.
     * @param uri IMAP server URI
     * @param listener client listener for connect/disconnect events
     * @return newly created session object
     * @throws IMAPSessionException on error
     */
    public IMAPSession createSession(URI uri, IMAPClientListener listener) throws IMAPSessionException {
        return new IMAPSession(uri, bootstrap, group, listener);
    }

    /**
     * End a session contained within a client.
     *
     * @param session
     */
    public void endSession(IMAPSession session) {
        session.disconnect();
    }

    /**
     * Close all of the sessions within a client, and shutdown the event group.
     */
    public void close() {
        this.group.shutdownGracefully();
    }

}