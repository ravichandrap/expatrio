import React, {FC} from 'react';

export const CreateUser: FC = () => {

    return <div className="create-user">
        <form action="/action_page.php">
            <div className="container">
                <h1>User Register</h1>
                <p>Please fill in this form to create an account.</p>
                <hr/>

                <label htmlFor="email"><b>User Id</b></label>
                <input type="text" name="firstName" id="firstName" readOnly/>

                <label htmlFor="email"><b>First Name</b></label>
                <input type="text" placeholder="Enter Email" name="firstName" id="firstName" required/>


                <label htmlFor="email"><b>Last Name</b></label>
                <input type="text" placeholder="Enter Last Name" name="lastName" id="lastName" required/>


                <label htmlFor="email"><b>Email</b></label>
                <input type="text" placeholder="Enter Email" name="email" id="email" required/>

                <label htmlFor="psw"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="psw" id="psw" required/>

                <label htmlFor="email"><b>Phone Number</b></label>
                <input type="text" placeholder="Enter Phone Number" name="phoneNumber" id="phoneNumber" required/>

                <label htmlFor="email"><b>User Role</b></label>
                <select>
                    <option>Admin</option>
                    <option>Customer</option>
                    <option>User</option>
                </select>

                <hr/>
                <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>

                <button type="submit" className="registerbtn">Register</button>
            </div>

            <div className="container signin">
                <p>Already have an account? <a href="#">Sign in</a>.</p>
            </div>
        </form>
    </div>

};