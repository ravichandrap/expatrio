import {UsersState} from "../typings/UsersState";
import {User} from "../typings/User";
import { CREATE_USER_PAGE, LOGIN_PAGE } from "./Constants";

export function getUserInitialState() : UsersState {
    return {
        users:[],
        authorization:"",
        isLoading: false,
        user: {} as User,
        editUser: {} as User,
        error: null,
        inputErrorMessage:'',
        currentPage: CREATE_USER_PAGE, 
        message: ""
    }
}