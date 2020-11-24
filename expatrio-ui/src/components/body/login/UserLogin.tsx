import React, {FC} from 'react';

export const UserLogin: FC = () => {
//style="background-color:#f1f1f1"
    return <div className="create-user">
        <form action="/action_page.php" method="post">
            <div className="container">
                <h1>Login Form</h1>
                <p>Please enter login details.</p>
                <hr/>
                <label htmlFor="uname"><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="uname" required/>
                    <label htmlFor="psw"><b>Password</b></label>
                    <input type="password" placeholder="Enter Password" name="psw" required/>
                        <button type="submit" className="registerbtn">Login</button>
            </div>
        </form>
    </div>
};