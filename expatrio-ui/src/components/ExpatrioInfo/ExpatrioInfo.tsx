import React, {FC} from 'react';
import { Dashboard } from '../dashboard/Dashboard';
import { ExpatrioHeader } from '../header/ExpatrioHeader';
import { UsersProvider } from '../hooks/UsersProvider';


export const ExpatrioInfo: FC = () => {

    return <UsersProvider>
        <ExpatrioHeader/>
        <Dashboard/>
    </UsersProvider>
};