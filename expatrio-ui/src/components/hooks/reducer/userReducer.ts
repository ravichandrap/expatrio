import {UsersState} from "../../typings/UsersState";
import {UsersAction} from "../../typings/UsersAction";
import {IS_LOADING, REQUEST_ERROR, SET_USERS} from "../../utils/Constants";

export function userReducer(state: UsersState, action: UsersAction): UsersState {

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
                users: action.users
            }
        default:
            return state;
    }
}