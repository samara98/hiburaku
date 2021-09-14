import { ApolloServerPluginLandingPageGraphQLPlayground } from 'apollo-server-core';
import { ApolloServer } from 'apollo-server-express';
import cookieParser from 'cookie-parser';
import express from 'express';
import createHttpError from 'http-errors';
import morgan from 'morgan';
import path from 'path';
import schema from './gql/schema';
import context from './gql/context';

export const app = express();
export const apolloServer = new ApolloServer({
  schema,
  context,
  plugins: [ApolloServerPluginLandingPageGraphQLPlayground()],
});
export const startApolloServer = async (cb?: () => void) => {
  await apolloServer.start();

  app.use(morgan('dev'));
  app.use(express.json());
  app.use(express.urlencoded({ extended: false }));
  app.use(express.static(path.join(__dirname, '..', 'public')));
  app.use(cookieParser());

  app.get('/', (req, res, next) => {
    return res.render('index', { title: 'Express' });
  });

  apolloServer.applyMiddleware({ app, path: '/gql', cors: true });

  // catch 404 and forward to error handler
  app.use(function (req, res, next) {
    return next(createHttpError(404));
  });
  // error handler
  app.use(function (err, req, res, next) {
    // set locals, only providing error in development
    res.locals.message = err.message;
    res.locals.stack =
      req.app.get('env') === 'development'
        ? err.stack.split('\n').map((el) => el.trim())
        : undefined;
    res.locals.error = req.app.get('env') === 'development' ? err : {};

    // render the error page
    res.status(err.status || 500);
    return next();
  });
  app.use(async (req, res) => {
    const result = { ...res.locals, meta: { status: res.statusCode } };
    return res.json(result);
  });

  if (cb) cb();
};
