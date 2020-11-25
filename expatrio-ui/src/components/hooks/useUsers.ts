import { useReducer } from 'react';
import {UsersScheme} from "../typings/UsersScheme";
import {getUserInitialState} from "../utils/getUserInitialState";
import {User} from "../typings/User";
import {fetchUsers} from "../api/APIService";
import {userReducer} from "./reducer/userReducer";

const  initialState = getUserInitialState();
export function useUsers(): UsersScheme {
    const [state, dispatch] = useReducer(userReducer, initialState);

    return {
        ...state,
        SetUsers(users: User[]) {
            fetchUsers("user", dispatch)
        }
    }
}