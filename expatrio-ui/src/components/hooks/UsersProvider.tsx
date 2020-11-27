import { log } from "console";
import React, { createContext, FC } from "react";
import { UsersScheme } from "../typings/UsersScheme";
import {useUsers} from './useUsers'

export const UsersContext = createContext({} as UsersScheme);

export const UsersProvider:FC = ({children}) => {
    const state = useUsers();
    
    return (<UsersContext.Provider value={state}>
        {children}
    </UsersContext.Provider>)
}


