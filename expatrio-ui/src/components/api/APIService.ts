import { getEnvironmentVariables } from "../utils/getEnvironmentVariables";
import { Dispatch } from "react";
import { UsersAction } from "../typings/UsersAction";
import { IS_LOADING, REQUEST_ERROR, SET_USERS, LOG_IN, SET_MESSAGE } from "../utils/Constants";
import { User } from "../typings/User";
import axios, { AxiosResponse } from 'axios';

const { BASE_URL } = getEnvironmentVariables();

const getURL = (role: string) => `${BASE_URL}/role/${role}`;
const deleteURL = (id: string) => `${BASE_URL}/${id}`;
const postURL = () => BASE_URL
const loginURL = () => `${BASE_URL}/login`

const urlUtil = () => {
    return {
        LOGIN_URL: `${BASE_URL}/auth/loginuser`,
        ALL_USERS_URL: `http://localhost:8081/api/v1/user`,
    }
}

const getHeader = (authorization: string) => {
    return {
        headers: {
            Authorization: "Baere  " + authorization
        }
    }
}

export async function loginUser(email: string, password: string, dispatch: Dispatch<UsersAction>) {
    try {
        dispatch({ type: IS_LOADING });
        const { data }: AxiosResponse<UserResponse> = await axios.post(
                                        urlUtil().LOGIN_URL, 
                                        { email: email, password: password }
                                    );

        console.log(data);
        console.log("data.authorization:",data.authorization);

        dispatch({
            type: LOG_IN,
            authorization: data.authorization,
            user: data.user
        })
    } catch (error) {
        console.log(error);
        dispatch({ type: REQUEST_ERROR, error: error });
    }
}

export async function fetchUsers(role: string = "Customer",
        dispatch: Dispatch<UsersAction>
    ) {
        try {
            dispatch({ type: IS_LOADING });
            const { data }: AxiosResponse<User[]> = await axios.get(getURL(role));
            //dispatch({ type: SET_USERS, users: data });
        } catch (error) {
            dispatch({ type: REQUEST_ERROR, error: error.message });
        }
}

export async function fetchAllUsers(authorization: string,
        dispatch: Dispatch<UsersAction>
    ) {
        console.log("authorization: ", authorization);
        
        try {
            dispatch({ type: IS_LOADING });
            const { data }: AxiosResponse<AllUserResponse> = 
            await axios.get(
                            urlUtil().ALL_USERS_URL, 
                            getHeader(authorization)
                        );

            console.log("data: ", data.users);
            
            dispatch({ 
                type: SET_USERS, 
                users: data.users, 
                authorization: data.authorization 
            }); 

        } catch (error) {
            dispatch({ type: REQUEST_ERROR, error: error.message });
        }
}

export async function updateUser(user: User, dispatch: Dispatch<UsersAction>) {
    try {
        await axios.post(postURL(), user);
        dispatch({ type: SET_MESSAGE, message: "User has been create!" })
    } catch (error) {
        console.log(error);
        dispatch({ type: REQUEST_ERROR, error: error.message });
    }
}

export async function deleteUser(id: string, dispatch: Dispatch<UsersAction>) {
    try {
        await axios.delete(deleteURL(id));
        dispatch({ type: SET_MESSAGE, message: "User has been deleted!" })
    } catch (error) {
        console.log(error);
        dispatch({ type: REQUEST_ERROR, error: error.message });
    }
}

interface UserResponse {
    authorization: string;
    user: User;
}

interface AllUserResponse {
    authorization: string;
    users: User[];
}

