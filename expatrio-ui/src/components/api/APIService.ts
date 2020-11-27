import {getEnvironmentVariables} from "../utils/getEnvironmentVariables";
import {Dispatch} from "react";
import {UsersAction} from "../typings/UsersAction";
import {IS_LOADING, REQUEST_ERROR, SET_USERS, SET_USER, SET_MESSAGE} from "../utils/Constants";
import {User} from "../typings/User";
import axios, {AxiosResponse} from 'axios';

const { BASE_URL } = getEnvironmentVariables();

const getURL = (role: string) => `${BASE_URL}/role/${role}`;
const deleteURL = (id: string) => `${BASE_URL}/${id}`;
const postURL = ()=> BASE_URL
const loginURL = ()=> `${BASE_URL}/login`

export async function fetchUsers(role: string = "Customer",
    dispatch: Dispatch<UsersAction>
) {
    try {
        dispatch({ type: IS_LOADING });
        const { data }: AxiosResponse<User[]> = await axios.get(getURL(role));
        dispatch({ type: SET_USERS, users: data });
    } catch (error) {
        dispatch({ type: REQUEST_ERROR, error: error.message });
    }
}

export async function updateUser(user: User, dispatch: Dispatch<UsersAction>) {
    try {
        await axios.post(postURL(), user);
        dispatch({type: SET_MESSAGE, message: "User has been create!"})
    } catch (error) {
        console.log(error);
        dispatch({ type: REQUEST_ERROR, error: error.message });
    }
}

export async function deleteUser(id: string, dispatch: Dispatch<UsersAction>) {
    try {
        await axios.delete(deleteURL(id));
        dispatch({type: SET_MESSAGE, message: "User has been deleted!"})
    } catch (error) {
        console.log(error);
        dispatch({ type: REQUEST_ERROR, error: error.message });
    }
}


export async function loginUser(email: string, password: string, dispatch: Dispatch<UsersAction>) {
    try {
        dispatch({ type: IS_LOADING });
        const {data, status}  : AxiosResponse<User> = await axios.post(loginURL(), {email: email, password: password});

        console.log(data);
        console.log(status);
       
        dispatch({ type: SET_USER, user: data });
    } catch (error) {
        console.log(error);
        dispatch({ type: REQUEST_ERROR, error: error });    
    }
}
