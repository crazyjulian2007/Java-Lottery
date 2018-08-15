import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class Lottery extends JFrame {

	private static JPanel contentPane;
	private static JLabel lblChoseNum, lblFinal, lblQualifyNum, label;
	private static JButton btnReStart, btnStart, btnRanNum, btnReturn;
	private static List<Integer> selectNum = new ArrayList<>(); // collect chosed num
	private static List<JButton> myJButton = new ArrayList<>(); // create button
	private static List<JButton> choseJButton = new ArrayList<>(); // collect chosed bt
	private static List<Integer> ranNum = new ArrayList<>(); // collect chosed num1
	private static List<Integer> compareNum = new ArrayList<>(); // collect compare num1

	private static int pointNum = 6;
	private static String num = "";
	private static JProgressBar pgb;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Lottery frame = new Lottery();
					frame.setVisible(true);
					frame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							System.exit(0);
						}
					});

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Lottery() throws Exception {
		setTitle("我是大樂透喔~~");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 431, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(10, 405, 380, 134);
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNumber = new JLabel("選擇號碼");
		lblNumber.setBackground(new Color(0, 0, 0));
		GridBagConstraints gbc_lblNumber = new GridBagConstraints();
		gbc_lblNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumber.gridx = 0;
		gbc_lblNumber.gridy = 1;
		panel.add(lblNumber, gbc_lblNumber);
		lblNumber.setFont(new Font("微軟正黑體", Font.BOLD, 20));

		lblChoseNum = new JLabel("");
		GridBagConstraints gbc_lblChoseNum = new GridBagConstraints();
		gbc_lblChoseNum.anchor = GridBagConstraints.WEST;
		gbc_lblChoseNum.gridwidth = 3;
		gbc_lblChoseNum.insets = new Insets(0, 0, 5, 0);
		gbc_lblChoseNum.gridx = 1;
		gbc_lblChoseNum.gridy = 1;
		panel.add(lblChoseNum, gbc_lblChoseNum);
		lblChoseNum.setBorder(new EmptyBorder(1, 10, 0, 0));
		lblChoseNum.setBackground(new Color(0, 0, 0));
		lblChoseNum.setFont(new Font("微軟正黑體", Font.BOLD, 20));

		JLabel FinalNum = new JLabel("得獎號碼");
		FinalNum.setBackground(new Color(0, 0, 0));
		GridBagConstraints gbc_FinalNum = new GridBagConstraints();
		gbc_FinalNum.insets = new Insets(0, 0, 5, 5);
		gbc_FinalNum.gridx = 0;
		gbc_FinalNum.gridy = 2;
		panel.add(FinalNum, gbc_FinalNum);
		FinalNum.setFont(new Font("微軟正黑體", Font.BOLD, 20));

		lblFinal = new JLabel("");
		GridBagConstraints gbc_lblFinal = new GridBagConstraints();
		gbc_lblFinal.anchor = GridBagConstraints.WEST;
		gbc_lblFinal.gridwidth = 3;
		gbc_lblFinal.insets = new Insets(0, 0, 5, 0);
		gbc_lblFinal.gridx = 1;
		gbc_lblFinal.gridy = 2;
		panel.add(lblFinal, gbc_lblFinal);
		lblFinal.setBorder(new EmptyBorder(1, 10, 0, 0));
		lblFinal.setBackground(new Color(0, 0, 0));
		lblFinal.setFont(new Font("微軟正黑體", Font.BOLD, 20));

		label = new JLabel("符合號碼");
		label.setBackground(new Color(0, 0, 0));
		label.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 3;
		panel.add(label, gbc_label);

		lblQualifyNum = new JLabel("");
		lblQualifyNum.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblQualifyNum.setBorder(new EmptyBorder(1, 10, 0, 0));
		lblQualifyNum.setBackground(new Color(0, 0, 0));
		GridBagConstraints gbc_lblQualifyNum = new GridBagConstraints();
		gbc_lblQualifyNum.anchor = GridBagConstraints.WEST;
		gbc_lblQualifyNum.gridwidth = 3;
		gbc_lblQualifyNum.insets = new Insets(0, 0, 0, 5);
		gbc_lblQualifyNum.gridx = 1;
		gbc_lblQualifyNum.gridy = 3;
		panel.add(lblQualifyNum, gbc_lblQualifyNum);

		String str1 = "遊戲說明 :";
		String str2 = "請選擇6 個號碼，並按下開獎按鈕。";
		String str3 = "開獎完成後，可以按下重置，重新開始遊戲。";
		String str4 = "如果選錯號碼，請按下返回以便重選號碼，";
		String str4h = "也可以再按一次同一個號碼取消選號，";
		String str5 = "如果懶的選號，可以按下隨機選號。";
		String str6 = "如果已經選擇號碼後，但沒有靈感了，";
		String str7 = "也可以按下隨機選號，電腦將協助產生至6 個號碼。";

		String strMsg = "<html><body>" + str1 + "<br>" + str2 + "<br>" + str3 + "<br>" + str4 + "<br>" + str4h + "<br>"
				+ str5 + "<br>" + str6 + "<br>" + str7 + "<body></html>";

		JLabel lblNewLabel = new JLabel(strMsg);
		lblNewLabel.setBackground(new Color(135, 206, 250));
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(10, 550, 380, 195);
		contentPane.add(lblNewLabel);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(50, 340, 300, 55);
		contentPane.add(toolBar);

		btnStart = new JButton("開獎");
		toolBar.add(btnStart);
		btnStart.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
				new EmptyBorder(10, 10, 10, 10)));
		btnStart.setFont(new Font("微軟正黑體", Font.BOLD, 16));

		btnReStart = new JButton("重置");
		toolBar.add(btnReStart);
		btnReStart.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
				new EmptyBorder(10, 10, 10, 10)));
		btnReStart.setFont(new Font("微軟正黑體", Font.BOLD, 16));

		btnReturn = new JButton("上一步");
		toolBar.add(btnReturn);
		btnReturn.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
				new EmptyBorder(10, 10, 10, 10)));
		btnReturn.setFont(new Font("微軟正黑體", Font.BOLD, 16));

		btnRanNum = new JButton("隨機選號");
		toolBar.add(btnRanNum);
		btnRanNum.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnRanNum.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
				new EmptyBorder(10, 10, 10, 10)));
		btnRanNum.setFocusable(false);
		btnReStart.setFocusable(false);
		btnReturn.setFocusable(false);
		btnStart.setFocusable(false);

		pgb = new JProgressBar();
		pgb.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		pgb.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		pgb.setDoubleBuffered(true);
		pgb.setVisible(false);
		pgb.setStringPainted(true);
		pgb.setForeground(new Color(0, 140, 140));
		pgb.setString("產生號碼中，緣分到了號碼就會出來了");
		pgb.setToolTipText("");
		pgb.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		pgb.setIndeterminate(true);
		pgb.setBounds(50, 300, 300, 35);
		contentPane.add(pgb);

		btnRanNum.addActionListener(new ActLis());
		btnReturn.addActionListener(new ActLis());
		btnReStart.addActionListener(new ActLis());
		btnStart.addActionListener(new ActLis());

		createButton(7, 7);
	}

	static void createButton(int i1, int i2) throws Exception {
		int btName = 1;
		int btText = 1;

		for (int i = 0; i < i1; i++) {
			for (int j = 0; j < i2; j++) {

				JButton button = new JButton();
				button.setName(String.valueOf((btName++)));
				button.setText(String.valueOf((btText++)));
				button.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
						new EmptyBorder(10, 10, 10, 10)));
				button.setBounds(20 + 50 * j, 20 + 40 * i, 50, 40);
				button.setFont(new Font("微軟正黑體 Light", Font.BOLD, 12));
				button.setForeground(Color.black);
				button.setBackground(Color.WHITE);
				myJButton.add(button);

				// button.setFocusable(false);
				button.setFocusPainted(false);

				button.addActionListener(new ActLis());
				contentPane.add(button);

			}
		}
	}

	static class ActLis implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JButton jButton = (JButton) e.getSource();
			try {

				for (int i = 0; i < myJButton.size(); i++) {
					if (jButton.equals(myJButton.get(i))) {
						output_digit(myJButton.get(i));
					}
				}
				try {
					if (jButton.equals(btnReStart)) {
						// System.out.println("restart");
						num = "";
						selectNum.clear();
						compareNum.clear();
						ranNum.clear();
						lblQualifyNum.setText(num);
						lblChoseNum.setText(num);
						lblFinal.setText(num);
						for (JButton btn : myJButton) {
							btn.setBackground(Color.white);
							btn.setForeground(Color.black);
						}
						choseJButton.clear();

						btnReturn.setEnabled(true);
						btnRanNum.setEnabled(true);
						btnStart.setEnabled(true);

					} else if (jButton.equals(btnStart)) {
						// System.out.println("start");
						if (selectNum.size() == 6) {

							btnReturn.setEnabled(false);
							btnRanNum.setEnabled(false);
							btnStart.setEnabled(false);

							time();

							new Thread() {

								@Override
								public void run() {
									try {
										Thread.sleep(3400);
										compare(selectNum);
									} catch (Exception e) {
										e.printStackTrace();
									} finally {
										System.out.println("Finish~");
									}
								}
							}.start();

						} else {
							JOptionPane.showMessageDialog(null, "You Need to Choose Six Number !", "NO!!",
									JOptionPane.WARNING_MESSAGE);
						}

					} else if (jButton.equals(btnRanNum)) {
						if (selectNum.size() < 6) {
							// System.out.println("Random Number");
							btnReturn.setEnabled(false);
							btnRanNum.setEnabled(false);

							time1();

							new Thread() {

								@Override
								public void run() {
									try {
										Thread.sleep(3400);
										ranNumber();
										num = "";
										Collections.sort(selectNum);
										for (Integer strMsg : selectNum) {
											num += Integer.toString(strMsg) + ",";
										}
										lblChoseNum.setText(num);
									} catch (Exception e) {
										e.printStackTrace();
									} finally {
										System.out.println("Finish~");
									}
								}
							}.start();

						} else {
							JOptionPane.showMessageDialog(null, "You already select 6 numbers", "NO!!",
									JOptionPane.WARNING_MESSAGE);
						}

					} else if (jButton.equals(btnReturn)) {

						if (choseJButton.size() > 0) {
							System.out.println("return success");
							choseJButton.get(choseJButton.size() - 1).setBackground(Color.white);
							choseJButton.remove(choseJButton.size() - 1);
							selectNum.remove(selectNum.size() - 1);
							num = "";
							// Collections.sort(selectNum);
							for (Integer strMsg : selectNum) {
								num += Integer.toString(strMsg) + ",";
							}

							lblChoseNum.setText(num);

						} else {
							JOptionPane.showMessageDialog(null, "Nothing", "NO!!", JOptionPane.WARNING_MESSAGE);
							// System.out.println("return false");
						}

					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	static void time1() {
		try {
			Timer t = new Timer();
			System.out.println("Start: " + new Date());

			TimerTask show = new TimerTask() {

				@Override
				public void run() {
					try {
						pgb.setVisible(true);
						for (int i = 0; i < 40; i++) {
							// Thread.sleep(50);
							int num = (int) (Math.random() * 49);
							System.out.println(Integer.toString(i) + " " + num);

							myJButton.get(num).setForeground(Color.MAGENTA);
							myJButton.get(num).setBackground(Color.ORANGE);
							Thread.sleep(80);
							myJButton.get(num).setForeground(Color.WHITE);
							myJButton.get(num).setBackground(Color.white);
						}
						for (JButton jb : myJButton) {
							jb.setForeground(Color.WHITE);
						}
						pgb.setVisible(false);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};

			t.schedule(show, 0);
			// Thread.sleep(3400);
			System.out.println("Finish: " + new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void time() {
		try {
			Timer t = new Timer();
			System.out.println("Start: " + new Date());

			TimerTask show = new TimerTask() {

				@Override
				public void run() {
					try {
						pgb.setVisible(true);
						for (int i = 0; i < 40; i++) {
							// Thread.sleep(50);
							int num = (int) (Math.random() * 49);
							System.out.println(Integer.toString(i) + " " + num);

							myJButton.get(num).setForeground(Color.GRAY);

							Thread.sleep(80);
							myJButton.get(num).setForeground(Color.WHITE);

						}
						for (JButton jb : myJButton) {
							jb.setForeground(Color.WHITE);
						}
						pgb.setVisible(false);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};

			t.schedule(show, 0);
			// Thread.sleep(3400);
			System.out.println("Finish: " + new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void compare(List<Integer> list) throws Exception {
		String strMsg = "", strMessage = "";

		boolean isin, isthere;
		int ran;
		try {

			for (int i = 0; i < pointNum; i++) {
				ran = (int) (Math.random() * 49 + 1);
				isin = ranNum.contains(ran);
				if (isin == true) {
					i--;
				} else {
					ranNum.add(ran);
				}
			}
			for (JButton jbt : myJButton) {
				for (Integer str : ranNum) {

					if (jbt.getText().equals(str.toString())) {
						jbt.setBackground(Color.GRAY);
					}
				}
			}

			Collections.sort(ranNum);
			for (Integer it : ranNum) {
				strMsg += it.toString() + ",";
			}
			lblFinal.setText(strMsg);

			for (int i = 0; i < pointNum; i++) {
				isthere = ranNum.contains(selectNum.get(i));
				if (isthere == true) {
					compareNum.add(selectNum.get(i));
				}
			}
			for (JButton jbt : myJButton) {
				for (Integer str : compareNum) {

					if (jbt.getText().equals(str.toString())) {
						jbt.setBackground(Color.PINK);
					}
				}
			}

			Collections.sort(compareNum);
			for (Integer it : compareNum) {
				strMessage += it.toString() + ",";
			}
			lblQualifyNum.setText(strMessage);

			switch (compareNum.size()) {
			case 6:
				JOptionPane.showMessageDialog(null, "恭喜! 你猜中了 6 個號碼");
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "恭喜! 你猜中了 5 個號碼");
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "恭喜! 你猜中了 4 個號碼");
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "恭喜! 你猜中了 3 個號碼");
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "恭喜! 你猜中了 2 個號碼");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "恭喜! 你猜中了 1 個號碼");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Sorry! 你猜中了 0 個號碼");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void output_digit(JButton btn) throws Exception {
		boolean isin = selectNum.contains(btn.getText());
		if (selectNum.size() < pointNum) {
			if (isin == true) {
				btn.setBackground(Color.white);
				selectNum.remove(selectNum.indexOf(btn.getText()));
				choseJButton.remove(choseJButton.indexOf(btn));

				num = "";
				lblChoseNum.setText(num);

				for (int strMsg : selectNum) {
					num += Integer.toString(strMsg) + ",";
				}

				lblChoseNum.setText(num);

			} else {
				num = "";
				selectNum.add(Integer.parseInt(btn.getText()));
				btn.setBackground(Color.MAGENTA);
				choseJButton.add(btn);
				Collections.sort(selectNum);
				for (int strMsg : selectNum) {
					num += Integer.toString(strMsg) + ",";
				}
				lblChoseNum.setText(num);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You already select 6 numbers", "NO!!", JOptionPane.WARNING_MESSAGE);
		}
		// System.out.println("selectNumSize: " + selectNum.size() + "
		// ,choseJButtonSize: " + choseJButton.size() + "\n");
	}

	static void ranNumber() {
		try {
			int num = pointNum - selectNum.size(); // how many numbers we still need
			boolean isin;
			int ran;

			for (int i = 0; i < num; i++) {
				ran = (int) (Math.random() * 49 + 1);
				isin = selectNum.contains(ran);
				if (isin == true) {
					i--;
				} else {
					selectNum.add(ran);
				}
			}
			for (JButton jbt : myJButton) {
				for (int str : selectNum) {
					if (jbt.getText().equals(Integer.toString(str))) {
						jbt.setBackground(Color.MAGENTA);
					}
				}
			}
			Collections.sort(selectNum);
			System.out.println("Result");
			for (int i = 0; i < selectNum.size(); i++) {
				System.out.println(selectNum.get(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
