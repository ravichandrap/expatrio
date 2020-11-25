import {getEnvironmentVariables} from "../utils/getEnvironmentVariables";
import {Dispatch, useReducer} from "react";
import {UsersAction} from "../typings/UsersAction";
import {IS_LOADING, REQUEST_ERROR, SET_USERS} from "../utils/Constants";
import {User} from "../typings/User";
import axios, {AxiosResponse} from 'axios';

const { BASE_URL } = getEnvironmentVariables();

function getURL(role: string): string {
    return `${BASE_URL}/api/v1/user/role/${role}`;
}


export async function fetchUsers(role: string = "Customer",
    dispatch: Dispatch<UsersAction>
) {

    try {
        dispatch({ type: IS_LOADING });

        const url = getURL(role);
        const { data }: AxiosResponse<User[]> = await axios.get(url);
        console.log(data);
        console.log("====================")
        dispatch({ type: SET_USERS, users: data });
    } catch (error) {
        dispatch({ type: REQUEST_ERROR, error: error.message });
    }
}
