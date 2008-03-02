/*
 * Copyright (C) 2005-2007 Les Hazlewood
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the
 *
 * Free Software Foundation, Inc.
 * 59 Temple Place, Suite 330
 * Boston, MA 02111-1307
 * USA
 *
 * Or, you may view it online at
 * http://www.opensource.org/licenses/lgpl-license.php
 */
package org.jsecurity;

import org.jsecurity.authc.AuthenticationException;
import org.jsecurity.authc.AuthenticationToken;
import org.jsecurity.authc.Authenticator;
import org.jsecurity.authz.Authorizer;
import org.jsecurity.session.SessionFactory;
import org.jsecurity.subject.Subject;

/**
 * A <tt>SecurityManager</tt> executes all security operations for <em>all</em> Subjects (aka users) across a
 * single application.
 *
 * <p>The interface itself primarily exists as a convenience - it extends the {@link Authenticator},
 * {@link Authorizer}, and {@link SessionFactory} interfaces, thereby consolidating
 * these behaviors into a single point of reference.  For most JSecurity usages, this simplifies configuration and
 * tends to be a more convenient approach than referencing <code>Authenticator</code>, <code>Authorizer</code>, and
 * <code>SessionFactory</code> instances seperately;  instead one only needs to interact with a
 * single <tt>SecurityManager</tt> instance.</p>
 *
 * <p>In addition to the above three interfaces, three unique methods are provided by this interface by itself,
 * {@link #login}, {@link #logout} and {@link #getSubject}.  A {@link Subject Subject} executes
 * authentication, authorization, and session operations for a <em>single</em> user, and as such can only be
 * managed by <tt>A SecurityManager</tt> which is aware of all three capabilities.  The three parent interfaces on the
 * other hand do not 'know' about <tt>Subject</tt>s to ensure a clean separation of concerns.
 *
 * <p>Usage Note: In actuality the large majority of application programmers won't interact with a SecurityManager
 * very often, if at all.  <em>Most</em> application programmers only care about security operations for the currently
 * executing user.  In that case, the application programmer can call the
 * {@link #getSubject() getSubject()} method and then use that returned instance for continued interaction with
 * JSecurity.
 *
 * <p>Framework developers on the other hand might find working with an actual SecurityManager useful.
 *
 * @see DefaultSecurityManager
 *
 * @since 0.2
 * 
 * @author Les Hazlewood
 */
public interface SecurityManager extends Authenticator, Authorizer, SessionFactory {

    
    Subject login( AuthenticationToken authenticationToken ) throws AuthenticationException;

    /**
     * Logs out the specified Subject from the system.
     *
     * @param subjectIdentifier the identifier of the subject/user to log out.
     */
    void logout( Object subjectIdentifier );

    /**
     * Returns the <tt>Subject</tt> instance representing the currently executing user.
     * @return the <tt>Subject</tt> instance representing the currently executing user.
     */
    Subject getSubject();


}