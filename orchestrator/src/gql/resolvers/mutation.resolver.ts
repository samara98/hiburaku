import { ApolloError } from 'apollo-server-errors';
import axios from 'axios';
import { Context } from '../context';
import { InputEntertainment } from '../inputs';
import { Entertainment } from '../interfaces';

const Mutation = {
  // movies
  createMovie: async (_, { data }: { data: InputEntertainment }, { movieServer }: Context) => {
    try {
      const newMovie = await movieServer.post<Entertainment>('/movies', data);
      return newMovie.data;
    } catch (err) {
      if (axios.isAxiosError(err)) {
        throw new ApolloError(err.response?.data.message);
      }
      throw new ApolloError('');
    }
  },
  updateMovie: async (
    _,
    { movieId, data }: { movieId: number; data: InputEntertainment },
    { movieServer }: Context,
  ) => {
    try {
      const newMovie = await movieServer.patch<Entertainment>(`/movies/${movieId}`, data);
      return newMovie.data;
    } catch (err) {
      if (axios.isAxiosError(err)) {
        throw new ApolloError(err.response?.data.message);
      }
      throw new ApolloError('');
    }
  },
  deleteMovie: async (_, { movieId }: { movieId: number }, { movieServer }: Context) => {
    try {
      await movieServer.delete(`/movies/${movieId}`);
      return;
    } catch (err) {
      if (axios.isAxiosError(err)) {
        throw new ApolloError(err.response?.data.message);
      }
      throw new ApolloError('');
    }
  },

  // tv
  createTv: async (_, { data }: { data: InputEntertainment }, { tvServer }: Context) => {
    try {
      const newMovie = await tvServer.post<Entertainment>('/tv', data);
      return newMovie.data;
    } catch (err) {
      if (axios.isAxiosError(err)) {
        throw new ApolloError(err.response?.data.message);
      }
      throw new ApolloError('');
    }
  },
  updateTv: async (
    _,
    { tvId, data }: { tvId: number; data: InputEntertainment },
    { tvServer }: Context,
  ) => {
    try {
      const newMovie = await tvServer.patch<Entertainment>(`/tv/${tvId}`, data);
      return newMovie.data;
    } catch (err) {
      if (axios.isAxiosError(err)) {
        throw new ApolloError(err.response?.data.message);
      }
      throw new ApolloError('');
    }
  },
  deleteTv: async (_, { tvId }: { tvId: number }, { tvServer }: Context) => {
    try {
      await tvServer.delete(`/tv/${tvId}`);
      return;
    } catch (err) {
      if (axios.isAxiosError(err)) {
        throw new ApolloError(err.response?.data.message);
      }
      throw new ApolloError('');
    }
  },
};

export default Mutation;
