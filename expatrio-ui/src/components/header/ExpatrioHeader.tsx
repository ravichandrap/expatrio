import React, {FC, useContext} from 'react';
import { UsersContext } from '../hooks/UsersProvider';
import { User } from '../typings/User';
import { CREATE_USER_PAGE, LOGIN_PAGE,USERS_PAGE } from '../utils/Constants';

export const ExpatrioHeader: FC = () => {
    const {currentPage, 
        SetCurrentPage,
        user} = useContext(UsersContext);
    
    return <div className="header">
        <a href="#default" className="logo">Expatrio</a>
        <div className="header-right">
            {getUserName(user)}
            <a href="#users" 
                className={isActiveClass(currentPage, CREATE_USER_PAGE)}
                onClick={()=>SetCurrentPage(CREATE_USER_PAGE)}
                >Create User</a>
            <a href="#create" 
                className={isActiveClass(currentPage, USERS_PAGE)}
                onClick={()=>SetCurrentPage(USERS_PAGE)}
                >Users</a>
            <a href="#login" 
                className={isActiveClass(currentPage, LOGIN_PAGE)}
                onClick={()=>SetCurrentPage(LOGIN_PAGE)}
                >{isLoggedIn(user)}</a>
        </div>
    </div>
}

const isActiveClass = (currentePage:string, page:string)=>  currentePage === page ? 'active': '';
const isLoggedIn = (user:User) => user.email ?  "Logout": "Login";
const getUserName = (user:User) => user.email ? 
            <a href="#user">{user.firstName+','+user.lastName}</a>: "";