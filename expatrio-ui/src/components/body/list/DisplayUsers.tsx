import React, {FC, useContext, useEffect, useState} from 'react';
import { UsersContext } from '../../hooks/UsersProvider';

export const DisplayUsers: FC = () => {
    const {GetUsers, users, error,message, EditUser, DeleteUser} = useContext(UsersContext);
    const [role, setRole] = useState("");
    const selectRole = (e: React.ChangeEvent<HTMLSelectElement>) =>  setRole(e.target.value);

    useEffect(()=>{
        GetUsers("all")
    }, [])
    
    const showMessage = error || message ? <td className="message">{error || message}</td>: "";

    const usersMap = users.map((user,index)=>{
        return <tr className={ index % 2 === 0 ? "odd": "even" } key={user.id+ index}>
            <td width="5%">{user.id}</td>
            <td width="15%">{user.firstName}</td>
            <td width="15%">{user.lastName}</td>
            <td width="20%">{user.email}</td>
            <td width="20%">{user.phoneNumber}</td>
            <td width="5%">{user.role}</td>
            <td width="5%">
                <button className="editbtn" onClick={()=> EditUser(user)}>Edit</button>
                <button className="deletebtn" onClick={()=>DeleteUser(user.id)}>Delete</button>
            </td>
         </tr>
    })

    return <div className="users-list">
        
        <table>
            <tr>
                <td>
                <select onChange={selectRole}>
                    <option>Select Role</option>
                    <option selected={role === "Admin"}>Admin</option>
                    <option selected={role === "Customer"}>Customer</option>
                    <option selected={role === "User"}>User</option>
                </select>
            </td>
        <td><button className="filter-btn" onClick={()=>GetUsers(role)}>Filter</button> </td>
        {showMessage}
        </tr>
        </table>
        <table width="100%">
        {header}
        {usersMap}
        </table>
       
    </div>
};

const header = () => <tr className="users-head">
                        <th >Id</th>
                        <th >First Name</th>
                        <th >Last Name</th>
                        <th >Email</th>
                        <th >Phone number</th>
                        <th >Role</th>
                        <th >Action</th>
                    </tr>