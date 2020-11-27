import {UsersState} from "../typings/UsersState";
import {User} from "../typings/User";
import { LOGIN_PAGE } from "./Constants";

export function getUserInitialState() : UsersState {
    return {
        users:[],
        isLoading: false,
        user: {} as User,
        editUser: {} as User,
        error: null,
        inputErrorMessage:'',
        currentPage: LOGIN_PAGE, 
        message: ""
    }
}