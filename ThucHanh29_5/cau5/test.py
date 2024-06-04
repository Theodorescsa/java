import pmdarima as pm
from sklearn.metrics import mean_squared_error
import numpy as np
import pandas as pd
# Chọn ngôn ngữ để dự đoán (ví dụ: Python)
language = "Python"
filename = 'Popularity of Programming Languages from 2005 to 2022.csv'
data = pd.read_csv(filename)
# Tạo mô hình ARIMA
model = pm.auto_arima(data[language], seasonal=True, m=12)

# Dự đoán cho 5 năm tiếp theo
future_forecast = model.predict(n_periods=60)

# Vẽ biểu đồ dự đoán
plt.figure(figsize=(15, 8))
plt.plot(data.index, data[language], label='Historical Data')
future_dates = pd.date_range(start=data.index[-1], periods=61, freq='M')[1:]
plt.plot(future_dates, future_forecast, label='Forecast', linestyle='dashed')
plt.title(f'Popularity Forecast for {language} (Next 5 Years)')
plt.xlabel('Year')
plt.ylabel('Popularity')
plt.legend()
plt.show()
