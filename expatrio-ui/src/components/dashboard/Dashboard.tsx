import React, {FC, useContext} from 'react';
import {DisplayUsers} from "../body/list/DisplayUsers";
import {CreateUser} from "../body/create/CreateUser";
import {UserLogin} from "../body/login/UserLogin";
import { UsersContext } from '../hooks/UsersProvider';
import { LOGIN_PAGE, CREATE_USER_PAGE, USERS_PAGE } from '../utils/Constants';


export const Dashboard: FC = () => {
    const {currentPage} = useContext(UsersContext);
    console.log(currentPage);

    return <div className="dashboard">
        {loadCurrentPage(currentPage)}
    </div>
};

const loadCurrentPage = (currentPage:string ) => {
    switch(currentPage) {
        case LOGIN_PAGE :
            return <UserLogin/>;
        case CREATE_USER_PAGE:
            return <CreateUser/>
        case USERS_PAGE:
            return <DisplayUsers/>
        default:
            return <UserLogin/>
    }
}