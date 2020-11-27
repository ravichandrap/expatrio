import { UsersState } from "../../typings/UsersState";
import { UsersAction } from "../../typings/UsersAction";
import {
    IS_LOADING,
    REQUEST_ERROR,
    SET_USERS,
    CURRENT_PAGE,
    SET_USER,
    USERS_PAGE,
    LOGIN_PAGE,
    LOG_OUT,
    EDIT_USER,
    CREATE_USER_PAGE,
    SET_MESSAGE
} from "../../utils/Constants";
import { User } from "../../typings/User";

export function userReducer(state: UsersState, action: UsersAction): UsersState {

    state = {
        ...state,
        message: "",
        editUser: {} as User
    }

    switch (action.type) {
        case REQUEST_ERROR:
            return {
                ...state,
                error: action.error
            }
        case IS_LOADING:
            return {
                ...state,
                isLoading: true
            }
        case SET_USERS:
            return {
                ...state,
                users: action.users,
            }
        case CURRENT_PAGE:
            return {
                ...state,
                currentPage: action.page,
            }
        case SET_USER:
            return {
                ...state,
                user: action.user,
                currentPage: USERS_PAGE,
            }
        case LOG_OUT:
            return {
                ...state,
                user: {} as User,
                currentPage: LOGIN_PAGE,
            }
        case EDIT_USER:
            return {
                ...state,
                editUser: action.user,
                currentPage: CREATE_USER_PAGE
            }
        case SET_MESSAGE:
            return {
                ...state,
                message: action.message
            }
            
        default:
            return state;
    }
}