import { useReducer } from 'react';
import {UsersScheme} from "../typings/UsersScheme";
import {getUserInitialState} from "../utils/getUserInitialState";
import {User} from "../typings/User";
import {fetchAllUsers, loginUser, updateUser, deleteUser} from "../api/APIService";
import {userReducer} from "./reducer/userReducer";
import { CURRENT_PAGE, EDIT_USER, LOG_OUT } from '../utils/Constants';
 
const  initialState = getUserInitialState();
export function useUsers(): UsersScheme {

    const [state, dispatch] = useReducer(userReducer, initialState);

    return {
        ...state,
        CreateOrUpdateUser(newUser:User) {
            updateUser(newUser, state.authorization, dispatch)
        },
        GetUsers(role: string) {
            fetchAllUsers(state.authorization, dispatch)
        },
        SetCurrentPage(currentPage: string) {
            dispatch({type: CURRENT_PAGE, page: currentPage})
        },
        LoginUser(email: string, password: string) {
            loginUser(email, password, dispatch);
        },
        LogOut(){
            dispatch({type: LOG_OUT})
        },
        EditUser(user: User) {
            dispatch({type: EDIT_USER, user: user})
        },
        DeleteUser(id: string) {
            deleteUser(id, dispatch)
        },
        Cleanup(){

        }, 
        GetAllUsers(){
            
        },
        UpdateUser(user: User) {
            updateUser(user, state.authorization, dispatch)
            fetchAllUsers(state.authorization, dispatch)
        }
    }
}
