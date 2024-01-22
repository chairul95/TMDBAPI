package com.example.pacinterviewtesting.movieDetail;


import com.example.pacinterviewtesting.model.detailMovie.DetailMovie;

public interface MovieDetailsContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(DetailMovie result);

            void onFailure(Throwable t);
        }

        void getMovieDetails(OnFinishedListener onFinishedListener, int movieId);
    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToViews(DetailMovie result);

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {
        void onDestroy();

        void requestMovieData(int movieId);
    }
}
