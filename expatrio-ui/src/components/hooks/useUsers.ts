import { useEffect,  useReducer } from 'react';
import {UsersScheme} from "../typings/UsersScheme";
import {getUserInitialState} from "../utils/getUserInitialState";
import {User} from "../typings/User";
import {fetchUsers} from "../api/APIService";
import {userReducer} from "./reducer/userReducer";
import { CURRENT_PAGE, LOGIN_PAGE } from '../utils/Constants';
 
const  initialState = getUserInitialState();
export function useUsers(): UsersScheme {

    const [state, dispatch] = useReducer(userReducer, initialState);

    useEffect(()=>{
        dispatch({type: CURRENT_PAGE, page: LOGIN_PAGE })
        fetchUsers("Customer", dispatch);
    },[]);

    return {
        ...state,
        SetUsers(users: User[]) {
            fetchUsers("user", dispatch)
        },
        SetCurrentPage(currentPage: string) {
            dispatch({type: CURRENT_PAGE, page: currentPage})
        }
    }
}