import { getRoles } from '@testing-library/react';
import React, {FC, useContext, useEffect, useState} from 'react';
import { UsersContext } from '../../hooks/UsersProvider';
import { User, Role } from '../../typings/User';

export const CreateUser: FC = () => {
    const {error, message, user, editUser ,CreateOrUpdateUser} = useContext(UsersContext);
    const [userDetails,setUserDetails] = useState({} as User);

    const updateUserDetails = (e: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;
        setUserDetails({
            ...userDetails,
            [name]: value
        })
    }

    const updateRole = (e: React.ChangeEvent<HTMLSelectElement>) => {
        const {value} = e.target;
        console.log("name:: ", value);
        
        setUserDetails({
            ...userDetails,
            roles: getRoles(Number(value))
        })

        console.log(userDetails);
        
    }

    const getRoles = (id:number): Role[] => id === 1 ? [{name:"Admin",id: 1001}]:[{name: "User", id: 1002}]

    const showMessage = error || message ? <p className="message">{error || message}</p>: "";

    useEffect(()=>{
        setUserDetails(editUser.id ? editUser: {} as User);
    }, [user, editUser])

    return <div className="create-user">
            <div className="container">
                <h1>User Register</h1>
                {showMessage}
                <hr/>

                <label htmlFor="email"><b>User Id</b></label>
                <input type="text" 
                        name="firstName" 
                        id="firstName" readOnly
                        value={userDetails.id}
                        onChange={updateUserDetails}/>

                <label htmlFor="email"><b>First Name</b></label>
                <input type="text" 
                        placeholder="Enter Email" 
                        name="firstName" id="firstName" required
                        value={userDetails.firstName}
                        onChange={updateUserDetails}/>

                <label htmlFor="email"><b>Last Name</b></label>
                <input type="text" 
                        placeholder="Enter Last Name" 
                        name="lastName" id="lastName" required
                        value={userDetails.lastName}
                        onChange={updateUserDetails}/>

                <label htmlFor="email"><b>Email</b></label>
                <input type="text" 
                        placeholder="Enter Email" 
                        name="email" id="email" required
                        value={userDetails.email}
                        onChange={updateUserDetails}/>

                <label htmlFor="psw"><b>Password</b></label>
                <input type="password" 
                        placeholder="Enter Password" 
                        name="psw" id="psw" required
                        value={userDetails.password}
                        onChange={updateUserDetails}/>

                <label htmlFor="email"><b>Phone Number</b></label>
                <input type="text" 
                        placeholder="Enter Phone Number" 
                        name="phoneNumber" id="phoneNumber" required
                        value={userDetails.phoneNumber}
                        onChange={updateUserDetails}/>

                <label htmlFor="email"><b>User Role</b></label>
                <select onChange={updateRole}>
                    <option value="1001">Admin</option>
                    <option value="1002">User</option>
                </select>

                <hr/>

                <button className="registerbtn"
                    onClick={()=>CreateOrUpdateUser(userDetails)}>
                    {userDetails.id ? 'Update':'Register'}
                </button>
            </div>
    </div>

};