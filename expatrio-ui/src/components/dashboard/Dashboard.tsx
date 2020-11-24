import React, {FC} from 'react';
import {Users} from "../body/list/Users";
import {CreateUser} from "../body/create/CreateUser";
import {UserLogin} from "../body/login/UserLogin";


export const Dashboard: FC = () => {

    return <div className="dashboard">
        <Users/>
        <CreateUser/>
        <UserLogin/>
    </div>
};