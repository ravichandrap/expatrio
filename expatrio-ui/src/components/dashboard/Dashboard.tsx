import React, {FC, useContext} from 'react';
import {Users} from "../body/list/Users";
import {CreateUser} from "../body/create/CreateUser";
import {UserLogin} from "../body/login/UserLogin";
import { useUsers } from '../hooks/useUsers';
import { UsersContext } from '../hooks/UsersProvider';
import { LOGIN_PAGE, CREATE_USER_PAGE } from '../utils/Constants';


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
        default:
            return <UserLogin/>
    }
}