import React, {FC, useContext, useState} from 'react';
import { UsersContext } from '../../hooks/UsersProvider';
import { User } from '../../typings/User';

export const UserLogin: FC = () => { 
//style="background-color:#f1f1f1"

const {LoginUser, message} = useContext(UsersContext);
    const [user, setUser] = useState({
        email:"Samir.Jouni@example.com",
        password:"test1234"
    } as User);

    const updateLogin = (e: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;
        setUser({
            ...user,
            [name]: value
        })
    }

    return <div className="create-user">
            <div className="container">
                <h1>Login Form</h1>
                <p>{message}{message}</p>
                
                <hr/>

                <label htmlFor="uname"><b>E-Mail</b></label>
                <input type="text" 
                    name="email" 
                    id="firstName" 
                    value={user.email}
                    onChange={updateLogin}/>

                <label htmlFor="psw"><b>Password</b></label>
                <input type="password" 
                        name="password" 
                        id="password" 
                        value={user.password}
                        onChange={updateLogin}/>
                
                <button type="submit" className="registerbtn"
                onClick={()=>LoginUser(user.email, user.password)}>Login</button>
            </div>
    </div>
};