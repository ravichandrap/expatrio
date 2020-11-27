import React, {FC, useContext} from 'react';
import { UsersContext } from '../hooks/UsersProvider';
import { User } from '../typings/User';
import { CREATE_USER_PAGE, LOGIN_PAGE,USERS_PAGE } from '../utils/Constants';

export const ExpatrioHeader: FC = () => {
    const {currentPage, 
        SetCurrentPage,
        user,
        LogOut} = useContext(UsersContext);
    
    const showCreateUsers = (user:User) =>  user.id ? <a href="#create" 
        className={isActiveClass(currentPage, USERS_PAGE)}
        onClick={()=>SetCurrentPage(USERS_PAGE)}
        >Users</a> :""

        const isLoggedIn = user.email ? 
        <a href="#login" 
                className={isActiveClass(currentPage, LOGIN_PAGE)}
                onClick={()=>LogOut()}
                >Logout</a>: <a href="#login" 
                className={isActiveClass(currentPage, LOGIN_PAGE)}
                onClick={()=>SetCurrentPage(LOGIN_PAGE)}
                >Login</a> ;

    return <div className="header">
        <a href="#default" className="logo">Expatrio</a>
        <div className="header-right">
            {getUserName(user)}
            {showCreateUsers(user)}
            <a href="#users" 
                className={isActiveClass(currentPage, CREATE_USER_PAGE)}
                onClick={()=>SetCurrentPage(CREATE_USER_PAGE)}
                >Create User</a>
          {isLoggedIn}
            
        </div>
    </div>
}

const isActiveClass = (currentePage:string, page:string)=>  currentePage === page ? 'active': '';

const getUserName = (user:User) => user.email ? 
            <a href="#user">{user.firstName+','+user.lastName}</a>: "";
