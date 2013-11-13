/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;
import controller.CinemaController;
import controller.CineplexController;
import controller.MovieController;
import controller.ShowtimeController;
import entity.Cinema;
import entity.Cineplex;
import entity.Movie;
import entity.Showtime;
import helper.DateHelper;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import utils.Common;

/**
 *
 * @author Aloysius
 */
public class MovieSearchGUI extends javax.swing.JFrame {
    
    /**
     * Creates new form Search
     */
    private Map<String, Integer> cineplexMap = new HashMap<>();
    private Map<String, Integer> cinemaMap = new HashMap<>();
    private Map<String, Integer> movieMap = new HashMap<>();
    
    private LinkedList<Cineplex> cineplexes;
    private LinkedList<Cinema> cinemas;
    private LinkedList<Movie> movies;
    private LinkedList<Showtime> showtimes;
    
    public MovieSearchGUI() {
        initComponents();
        
        cineplexes = CineplexController.getCineplexList();
        cinemas = CinemaController.getCinemaList();
        selectCinemaLbl.setVisible(false);
        cinemaComboBox.setVisible(false);
        
        selectMovieLbl.setVisible(false);
        movieComboBox.setVisible(false);
        
        showtimeTable.setVisible(false);
        movieTitleLbl.setVisible(false);
        movieRatingLbl.setVisible(false);
        movieDescLbl.setVisible(false);
        this.getContentPane().setBackground(Color.WHITE);
        for (Cineplex c: cineplexes) {
            cineplexMap.put(c.getName(), c.getId());
            cineplexComboBox.addItem(c.getName());
        }
        
        for (Cinema c: cinemas) {
            cinemaMap.put(c.getName(), c.getId());
        }
        
        cineplexComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cinemaComboBox.removeAllItems();
                selectCinemaLbl.setVisible(true);
                cinemaComboBox.setVisible(true);
                for (Cinema c: cinemas) {
                    if (c.getCineplexId() == cineplexMap.get((String)cineplexComboBox.getSelectedItem()))
                        cinemaComboBox.addItem(c.getName());
                }
            }
        });
        
        cinemaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movieComboBox.removeAllItems();
                selectMovieLbl.setVisible(true);
                movieComboBox.setVisible(true);
                try {
                    movies = MovieController.getMoviesByCinema(cinemaMap.get((String)cinemaComboBox.getSelectedItem()));
                    movieMap = new HashMap<>();
                    if (movies.size() > 0) {
                        for (Movie m: movies) {
                            movieMap.put(m.getName(), m.getId());
                            movieComboBox.addItem(m.getName());
                        }
                        showtimeTable.setVisible(true);
                    } else {
                        movieComboBox.addItem("NO MOVIES AVAILABLE");
                        showtimeTable.setVisible(false);
                    }
                } catch (NullPointerException exception) {
                }
            }
        });
        
        movieComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) showtimeTable.getModel();
                tableModel.setRowCount(0);
                String[] colName = {"Date", "Time"};
                tableModel.setColumnIdentifiers(colName);
                
                try {
                    showtimes = ShowtimeController.getShowtimesByMovieAndCinema(movieMap.get((String)movieComboBox.getSelectedItem()),cinemaMap.get((String)cinemaComboBox.getSelectedItem()));
                    for(Showtime s: showtimes) {
                        Object[] objects = new Object[2];
                        objects[0] = DateHelper.getDateStringFormat(s.getTime());
                        objects[1] = DateHelper.getTimeOnlyFormat(s.getTime());     
                        tableModel.addRow(objects);
                    }
                    showtimeTable.setModel(tableModel);
                    
                    movieTitleLbl.setVisible(true);
                    movieRatingLbl.setVisible(true);
                    movieDescLbl.setVisible(true);

                    Movie movie = MovieController.getMovieById(movieMap.get((String)movieComboBox.getSelectedItem()));
                    movieTitleLbl.setText(movie.getName());
                    movieRatingLbl.setText("Rating: " + movie.getRating() + " of 10");
                    movieDescLbl.setText("No description available for this movie.");
                } catch (NullPointerException exception) {
                    showtimeTable.setVisible(false);
                    movieTitleLbl.setVisible(false);
                    movieRatingLbl.setVisible(false);
                    movieDescLbl.setVisible(false);
                }
            }
        });
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cineplexComboBox = new javax.swing.JComboBox();
        logoLbl = new javax.swing.JLabel();
        cinemaComboBox = new javax.swing.JComboBox();
        selectCineplexLbl = new javax.swing.JLabel();
        selectCinemaLbl = new javax.swing.JLabel();
        selectMovieLbl = new javax.swing.JLabel();
        movieComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        showtimeTable = new javax.swing.JTable();
        movieTitleLbl = new javax.swing.JLabel();
        movieDescLbl = new javax.swing.JLabel();
        movieRatingLbl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MOBLIMA App");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(760, 520));
        getContentPane().setLayout(null);

        cineplexComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cineplexComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(cineplexComboBox);
        cineplexComboBox.setBounds(20, 160, 350, 20);

        logoLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/moblima.png"))); // NOI18N
        getContentPane().add(logoLbl);
        logoLbl.setBounds(20, 0, 230, 130);

        cinemaComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cinemaComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(cinemaComboBox);
        cinemaComboBox.setBounds(20, 210, 350, 20);

        selectCineplexLbl.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        selectCineplexLbl.setForeground(new java.awt.Color(255, 255, 255));
        selectCineplexLbl.setText("Select a cineplex");
        getContentPane().add(selectCineplexLbl);
        selectCineplexLbl.setBounds(20, 140, 350, 15);

        selectCinemaLbl.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        selectCinemaLbl.setForeground(new java.awt.Color(255, 255, 255));
        selectCinemaLbl.setText("Select a cinema");
        getContentPane().add(selectCinemaLbl);
        selectCinemaLbl.setBounds(20, 190, 350, 15);

        selectMovieLbl.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        selectMovieLbl.setForeground(new java.awt.Color(255, 255, 255));
        selectMovieLbl.setText("Select a movie");
        getContentPane().add(selectMovieLbl);
        selectMovieLbl.setBounds(20, 240, 350, 15);

        movieComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movieComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(movieComboBox);
        movieComboBox.setBounds(20, 260, 350, 20);

        showtimeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        showtimeTable.setToolTipText("");
        jScrollPane1.setViewportView(showtimeTable);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(400, 10, 330, 470);

        movieTitleLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        movieTitleLbl.setForeground(new java.awt.Color(255, 255, 255));
        movieTitleLbl.setText("#movie.title");
        getContentPane().add(movieTitleLbl);
        movieTitleLbl.setBounds(20, 300, 350, 17);

        movieDescLbl.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        movieDescLbl.setForeground(new java.awt.Color(255, 255, 255));
        movieDescLbl.setText("#movie.desc");
        getContentPane().add(movieDescLbl);
        movieDescLbl.setBounds(20, 340, 350, 15);

        movieRatingLbl.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        movieRatingLbl.setForeground(new java.awt.Color(255, 255, 255));
        movieRatingLbl.setText("#movie.rating");
        getContentPane().add(movieRatingLbl);
        movieRatingLbl.setBounds(20, 320, 350, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 140, 730, 340);

        getAccessibleContext().setAccessibleName("Frame");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cineplexComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cineplexComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cineplexComboBoxActionPerformed

    private void cinemaComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cinemaComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cinemaComboBoxActionPerformed

    private void movieComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movieComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_movieComboBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MovieSearchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MovieSearchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MovieSearchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MovieSearchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        try {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Common.initDB();
                new MovieSearchGUI().setVisible(true);
            }
        });
        } catch (Exception e){
            
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cinemaComboBox;
    private javax.swing.JComboBox cineplexComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logoLbl;
    private javax.swing.JComboBox movieComboBox;
    private javax.swing.JLabel movieDescLbl;
    private javax.swing.JLabel movieRatingLbl;
    private javax.swing.JLabel movieTitleLbl;
    private javax.swing.JLabel selectCinemaLbl;
    private javax.swing.JLabel selectCineplexLbl;
    private javax.swing.JLabel selectMovieLbl;
    private javax.swing.JTable showtimeTable;
    // End of variables declaration//GEN-END:variables
}
